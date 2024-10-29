let listaProveedores = []
const tBody = document.querySelector("#tBody")
const tipoAccion = document.querySelector("#actionType")

obtenerProveedores();

function action(){
    switch(tipoAccion.value){
        case "crear":
            crearProveedor()
            $('#popUpProviders').modal('hide')
            break;
        case "editar":
            modificarProveedor()
            $('#popUpProviders').modal('hide')
            break;
        default:
            eliminarProveedor(txtId.value)
            $('#popUpEliminar').modal('hide')
            break;
    }
}

function obtenerProveedores(){
    const requestOptions = {
        method: "GET",
        redirect: "follow"
    };
    
    fetch("http://localhost:8080/api/proveedores/todos", requestOptions)
        .then((response) => response.json())
        .then((result) => {
            listaProveedores = result
                result.forEach(element => {
                    tBody.innerHTML +=
                    `
                    <tr>
                        <th scope="row">${element.id}</th>
                        <td>${element.nombreEmpresa}</td>
                        <td>${element.encargado}</td>
                        <td>${element.telefono}</td>
                        <td>${element.correo}</td>
                        <td>${element.direccion}</td>
                        <td><i class="fa-regular fa-pencil" onclick="editarProveedor(${element.id})" data-bs-toggle="modal" data-bs-target="#popUpProviders"></i></td>
                        <td><i class="fa-regular fa-trash-can" onclick="confirmarEliminacion(${element.id})" data-bs-toggle="modal" data-bs-target="#popUpEliminar"></i></td>
                    </tr>
                    `
                });
        })
        .catch((error) => console.error(error));
}

function crearProveedor(){
    const txtName = document.querySelector("#txtName");
    const txtManager = document.querySelector("#txtManager");
    const txtPhone = document.querySelector("#txtPhone");
    const txtEmail = document.querySelector("#txtEmail");
    const txtAddress = document.querySelector("#txtAddress");
    const myHeaders = new Headers();
    myHeaders.append("Content-Type", "application/json");
    const raw = JSON.stringify({
        "nombreEmpresa": txtName.value,
        "encargado": txtManager.value,
        "telefono": txtPhone.value,
        "correo": txtEmail.value,
        "direccion": txtAddress.value
    });
    const requestOptions = {
    method: "POST",
    headers: myHeaders,
    body: raw,
    redirect: "follow"
    };
    fetch("http://localhost:8080/api/proveedores/crear", requestOptions)
    .then((response) => response.json())
    .then((element) => {
        tBody.innerHTML += 
        `
        <tr>
            <th scope="row">${element.id}</th>
            <td>${element.nombreEmpresa}</td>
            <td>${element.encargado}</td>
            <td>${element.telefono}</td>
            <td>${element.correo}</td>
            <td>${element.direccion}</td>
            <td><i class="fa-regular fa-pencil" onclick="editarProveedor(${element.id})" data-bs-toggle="modal" data-bs-target="#popUpProviders"></i></td>
            <td><i class="fa-regular fa-trash-can" onclick="confirmarEliminacion(${element.id})" data-bs-toggle="modal" data-bs-target="#popUpEliminar"></i></td>
        </tr>
        `
        listaProveedores.push();
    })
    .catch((error) => console.error(error));
}

function agregarProveedor(){
    txtId.value = ""
    txtName.value = ""
    txtManager.value = ""
    txtPhone.value = ""
    txtEmail.value = ""
    txtAddress.value = ""
    actionType.value = "crear"
}

function modificarProveedor(){

    const myHeaders = new Headers();
    myHeaders.append("Content-Type", "application/json");

    const raw = JSON.stringify({
        "id": txtId.value,
        "nombreEmpresa": txtName.value,
        "encargado": txtManager.value,
        "telefono": txtPhone.value,
        "correo": txtEmail.value,
        "direccion": txtAddress.value
    });

    const requestOptions = {
    method: "PUT",
    headers: myHeaders,
    body: raw,
    redirect: "follow"
    };

    fetch("http://localhost:8080/api/proveedores/actualizar", requestOptions)
    .then((response) => response.json())
    .then(() => {
        listaProveedores = []
        tBody.innerHTML = ""
        obtenerProveedores()
    })
    .catch((error) => console.error(error));
}

function editarProveedor(id){
    const proveedor = listaProveedores.find(element => element.id === id)
    txtId.value = id
    txtName.value = proveedor.nombreEmpresa
    txtManager.value = proveedor.encargado
    txtPhone.value = proveedor.telefono
    txtEmail.value = proveedor.correo
    txtAddress.value = proveedor.direccion
    tipoAccion.value = "editar"
}

function confirmarEliminacion(id){
    const txtNameD = document.querySelector("#txtNameD");
    const txtManagerD = document.querySelector("#txtManagerD");
    const txtPhoneD = document.querySelector("#txtPhoneD");
    const txtEmailD = document.querySelector("#txtEmailD");
    const txtAddressD = document.querySelector("#txtAddressD");
    const proveedor = listaProveedores.find(element => element.id === id)
    txtId.value = id
    txtNameD.value = proveedor.nombreEmpresa
    txtManagerD.value = proveedor.encargado
    txtPhoneD.value = proveedor.telefono
    txtEmailD.value = proveedor.correo
    txtAddressD.value = proveedor.direccion
    tipoAccion.value = "borrar"
}

function eliminarProveedor(id){
    const requestOptions = {
        method: "DELETE",
        redirect: "follow"
    };

    fetch(`http://localhost:8080/api/proveedores/borrar/${id}`, requestOptions)
        .then((response) => response.text())
        .then(() => {
            listaProveedores = []
            tBody.innerHTML = ''
            obtenerProveedores()
        })
        .catch((error) => console.error(error));
}
