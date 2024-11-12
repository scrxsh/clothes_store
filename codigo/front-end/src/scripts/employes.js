let listaEmpleados = []
const tBodyE = document.querySelector("#tBodyE")
const tipoAccionE = document.querySelector("#actTypeE")

function actionE(){
    switch(tipoAccion.value){
        case "crear":
            crearEmpleado()
            break;
        case "editar":
            modificarEmpleado()
            break;
        default:
            closeModal('deleteModalC')
            eliminarEmpleado(idEmpleado.value)
            break;
    }
}

function validacionFormularioE(){
    const formatoEmail = /^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,6}$/;
    const formatoTelefono = /^3[0-9]{9}$/;
    
    if( nombreEmpleado.value.trim() === "" || nDocEmpleado.value.trim() === "" || telefonoEmpleado.value.trim() === "" || emailEmpleado.value.trim() ===  "" || direccionEmpleado.value.trim() === "" || 
    !formatoEmail.test(emailEmpleado.value.trim()) || !formatoTelefono.test(telefonoEmpleado.value.trim()) ){
        alert("Por favor, completa todos los campos  antes de enviar el formulario.")
    }    
    else{
        actionE();
    }
}

obtenerEmpleados();

function obtenerEmpleados(){
    const requestOptions = {
        method: "GET",
        redirect: "follow"
    };
    
    fetch("http://localhost:8080/api/empleados/todos", requestOptions)
        .then((response) => response.json())
        .then((result) => {
            listaEmpleados = result
                result.forEach(element => {
                    tBodyE.innerHTML +=
                    `
                    <tr class="odd:bg-white even:bg-gray-50 border-b ">
                        <th scope="row" class="px-6 py-4 text-gray-900 whitespace-nowrap">${element.id}</th>
                        <th scope="row" class="px-6 py-4">${element.nombre}</th>
                        <td class="px-6 py-4">${element.n_documento}</td>
                        <td class="px-6 py-4">${element.email}</td>
                        <td class="px-6 py-4">${element.telefono}</td>
                        <td class="px-6 py-4">${element.direccion}</td>
                        <td class="px-6 py-4 -ml-5 flex space-x-2">
                            <i class="fa-solid fa-pen-circle text-green-600 text-3xl cursor-pointer" onclick="editarEmpleado('modalEmploye', ${element.id})"></i>
                            <i class="fa-solid fa-circle-trash text-red-600 text-3xl cursor-pointer" onclick="confirmarEliminacionE('deleteModalE',${element.id})"></i>
                        </td>
                    </tr>
                    `
                });
        })
        .catch((error) => console.error(error));
}

function crearEmpleado(){
    const nombreEmpleado = document.querySelector("#nombreEmpleado");
    const nDocEmpleado = document.querySelector("#nDocEmpleado");
    const telefonoEmpleado = document.querySelector("#telefonoEmpleado");
    const emailEmpleado = document.querySelector("#emailEmpleado");
    const direccionEmpleado = document.querySelector("#direccionEmpleado");

    const myHeaders = new Headers();
    myHeaders.append("Content-Type", "application/json");
    const raw = JSON.stringify({
        "nombre": nombreEmpleado.value.trim(),
        "n_documento": nDocEmpleado.value.trim(),
        "telefono": telefonoEmpleado.value.trim(),
        "email": emailEmpleado.value.trim(),
        "direccion": direccionEmpleado.value.trim()
    });
    const requestOptions = {
    method: "POST",
    headers: myHeaders,
    body: raw,
    redirect: "follow"
    };
    fetch("http://localhost:8080/api/empleados/crear", requestOptions)
    .then((response) => response.json())
    .then((element) => {
        tBody.innerHTML += 
        `
        <tr class="odd:bg-white even:bg-gray-50 border-b ">
            <th scope="row" class="px-6 py-4 text-gray-900 whitespace-nowrap">${element.id}</th>
            <th scope="row" class="px-6 py-4">${element.nombre}</th>
            <td class="px-6 py-4">${element.n_documento}</td>
            <td class="px-6 py-4">${element.email}</td>
            <td class="px-6 py-4">${element.telefono}</td>
            <td class="px-6 py-4">${element.direccion}</td>
            <td class="px-6 py-4 -ml-5 flex space-x-2">
                <i class="fa-solid fa-pen-circle text-green-600 text-3xl cursor-pointer" onclick="editarEmpleado('modalEmploye', ${element.id})"></i>
                <i class="fa-solid fa-circle-trash text-red-600 text-3xl cursor-pointer" onclick="confirmarEliminacionE('deleteModalE',${element.id})"></i>
            </td>
        </tr>
        `
        listaEmpleados.push(element);
    })
    .catch((error) => console.error(error));
}

function agregarEmpleado(modalId){
    openModal(modalId); 
    idEmpleado.value = ""
    nombreEmpleado.value = ""
    nDocEmpleado.value = ""
    telefonoEmpleado.value = ""
    emailEmpleado.value = ""
    direccionEmpleado.value = ""
    tipoAccion.value = "crear"
}

function modificarEmpleado() {
    const myHeaders = new Headers();
    myHeaders.append("Content-Type", "application/json");

    const raw = JSON.stringify({
        "id": idEmpleado.value,
        "nombre": nombreEmpleado.value.trim(),
        "n_documento": nDocEmpleado.value.trim(),
        "telefono": telefonoEmpleado.value.trim(),
        "email": emailEmpleado.value.trim(),
        "direccion": direccionEmpleado.value.trim()
    });

    const requestOptions = {
        method: "PUT",
        headers: myHeaders,
        body: raw,
        redirect: "follow"
    };

    fetch("http://localhost:8080/api/empleados/actualizar", requestOptions)
        .then((response) => response.json())
        .then(() => {
            listaEmpleados = [];
            tBodyE.innerHTML = '';
            obtenerEmpleados();
        })
        .catch((error) => console.error(error));
}

function editarEmpleado(modalId, id) {
    openModal(modalId); 
    const empleado = listaEmpleados.find(element => element.id === id);
    idEmpleado.value = id;
    nombreEmpleado.value = empleado.nombre;
    nDocEmpleado.value = empleado.n_documento;
    telefonoEmpleado.value = empleado.telefono;
    emailEmpleado.value = empleado.email;
    direccionEmpleado.value = empleado.direccion;
    tipoAccion.value = "editar"
}

function confirmarEliminacionE(modalId, id){
    openModal(modalId); 
    listaEmpleados.find(element => element.id === id)
    idEmpleado.value = id
    tipoAccion.value = "borrar"
}

function eliminarEmpleado(id){
    const requestOptions = {
        method: "DELETE",
        redirect: "follow"
    };

    fetch(`http://localhost:8080/api/empleados/borrar/${id}`, requestOptions)
        .then((response) => response.text())
        .then(() => {
            listaEmpleados = []
            tBodyE.innerHTML = ''
            obtenerEmpleados();
        })
        .catch((error) => console.error(error));
}