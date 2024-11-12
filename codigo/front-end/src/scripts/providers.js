let listaProveedores = []
const tBody = document.querySelector("#tBody")
const tipoAccion = document.querySelector("#actType")


function action(){
    switch(tipoAccion.value){
        case "crear":
            crearProveedor()
            break;
        case "editar":
            modificarProveedor()
            break;
        default:
            closeModal('deleteModal')
            eliminarProveedor(idEmpresa.value)
            break;
    }
}

function validacionFormulario(){
    const formatoEmail = /^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,6}$/;
    const formatoTelefono = /^3[0-9]{9}$/;
    
    if( nombreEmpresa.value.trim() === "" || nombreEncargado.value.trim() === "" || telefonoEncargado.value.trim() === "" || emailEmpresa.value.trim() ===  "" || direccionEmpresa.value.trim() === "" || 
    !formatoEmail.test(emailEmpresa.value.trim()) || !formatoTelefono.test(telefonoEncargado.value.trim()) ){
        alert("Por favor, completa todos los campos  antes de enviar el formulario.")
    }    
    else{
        action();
    }
}

obtenerProveedores();

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
                    <tr class="odd:bg-white even:bg-gray-50 border-b ">
                        <th scope="row" class="px-6 py-4 text-gray-900 whitespace-nowrap">${element.id}</th>
                        <th scope="row" class="px-6 py-4">${element.nombreEmpresa}</th>
                        <td class="px-6 py-4">${element.encargado}</td>
                        <td class="px-6 py-4">${element.telefono}</td>
                        <td class="px-6 py-4">${element.correo}</td>
                        <td class="px-6 py-4">${element.direccion}</td>
                        <td class="px-6 py-4 -ml-5 flex space-x-2">
                            <i class="fa-solid fa-pen-circle text-green-600 text-3xl cursor-pointer" onclick="editarProveedor('modalProvider', ${element.id})"></i>
                            <i class="fa-solid fa-circle-trash text-red-600 text-3xl cursor-pointer" onclick="confirmarEliminacion('deleteModal',${element.id})"></i>
                        </td>
                    </tr>
                    `
                });
        })
        .catch((error) => console.error(error));
}




function crearProveedor(){
    const nombreEmpresa = document.querySelector("#nombreEmpresa");
    const nombreEncargado = document.querySelector("#nombreEncargado");
    const telefonoEncargado = document.querySelector("#telefonoEncargado");
    const emailEmpresa = document.querySelector("#emailEmpresa");
    const direccionEmpresa = document.querySelector("#direccionEmpresa");

    const myHeaders = new Headers();
    myHeaders.append("Content-Type", "application/json");
    const raw = JSON.stringify({
        "nombreEmpresa": nombreEmpresa.value.trim(),
        "encargado": nombreEncargado.value.trim(),
        "telefono": telefonoEncargado.value.trim(),
        "correo": emailEmpresa.value.trim(),
        "direccion": direccionEmpresa.value.trim()
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
        <tr class="odd:bg-white even:bg-gray-50 border-b ">
            <th scope="row" class="px-6 py-4 text-gray-900 whitespace-nowrap">${element.id}</th>
            <th scope="row" class="px-6 py-4">${element.nombreEmpresa}</th>
            <td class="px-6 py-4">${element.encargado}</td>
            <td class="px-6 py-4">${element.telefono}</td>
            <td class="px-6 py-4">${element.correo}</td>
            <td class="px-6 py-4">${element.direccion}</td>
            <td class="px-6 py-4 -ml-5 flex space-x-2">
                <i class="fa-solid fa-pen-circle text-green-600 text-3xl cursor-pointer" onclick="editarProveedor('modalProvider', ${element.id})"></i>
                <i class="fa-solid fa-circle-trash text-red-600 text-3xl cursor-pointer" onclick="confirmarEliminacion('deleteModal',${element.id})"></i>
            </td>
        </tr>
        `
        listaProveedores.push(element);
    })
    .catch((error) => console.error(error));
}

function agregarProveedor(modalId){
    openModal(modalId); 
    idEmpresa.value = ""
    nombreEmpresa.value = ""
    nombreEncargado.value = ""
    telefonoEncargado.value = ""
    emailEmpresa.value = ""
    direccionEmpresa.value = ""
    tipoAccion.value = "crear"
}

function modificarProveedor() {
    const myHeaders = new Headers();
    myHeaders.append("Content-Type", "application/json");

    const raw = JSON.stringify({
        "id": idEmpresa.value,
        "nombreEmpresa": nombreEmpresa.value.trim(),
        "encargado": nombreEncargado.value.trim(),
        "telefono": telefonoEncargado.value.trim(),
        "correo": emailEmpresa.value.trim(),
        "direccion": direccionEmpresa.value.trim()
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
            listaProveedores = [];
            tBody.innerHTML = '';
            obtenerProveedores();
        })
        .catch((error) => console.error(error));
}

function editarProveedor(modalId, id) {
    openModal(modalId); 
    const proveedor = listaProveedores.find(element => element.id === id);
    idEmpresa.value = id;
    nombreEmpresa.value = proveedor.nombreEmpresa;
    nombreEncargado.value = proveedor.encargado;
    telefonoEncargado.value = proveedor.telefono;
    emailEmpresa.value = proveedor.correo;
    direccionEmpresa.value = proveedor.direccion;
    tipoAccion.value = "editar"
}

function confirmarEliminacion(modalId, id){
    openModal(modalId); 
    listaProveedores.find(element => element.id === id)
    idEmpresa.value = id
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
