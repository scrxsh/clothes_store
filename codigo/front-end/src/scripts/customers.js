let listaClientes = []
const tBody = document.querySelector("#tBody")
const tipoAccion = document.querySelector("#actType")


function action(){
    switch(tipoAccion.value){
        case "crear":
            crearCliente()
            break;
        case "editar":
            modificarCliente()
            break;
        default:
            closeModal('deleteModalC')
            eliminarCliente(idCliente.value)
            break;
    }
}

function validacionFormulario(){
    const formatoEmail = /^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,6}$/;
    const formatoTelefono = /^3[0-9]{9}$/;
    
    if( nombreCliente.value.trim() === "" || nDocCliente.value.trim() === "" || telefonoCliente.value.trim() === "" || emailCliente.value.trim() ===  "" || direccionCliente.value.trim() === "" || 
    !formatoEmail.test(emailCliente.value.trim()) || !formatoTelefono.test(telefonoCliente.value.trim()) ){
        alert("Por favor, completa todos los campos  antes de enviar el formulario.")
    }    
    else{
        action();
    }
}

obtenerClientes();

function obtenerClientes(){
    const requestOptions = {
        method: "GET",
        redirect: "follow"
    };
    
    fetch("http://localhost:8080/api/clientes/todos", requestOptions)
        .then((response) => response.json())
        .then((result) => {
            listaClientes = result
                result.forEach(element => {
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
                            <i class="fa-solid fa-pen-circle text-green-600 text-3xl cursor-pointer" onclick="editarCliente('modalCustomer', ${element.id})"></i>
                            <i class="fa-solid fa-circle-trash text-red-600 text-3xl cursor-pointer" onclick="confirmarEliminacion('deleteModalC',${element.id})"></i>
                        </td>
                    </tr>
                    `
                });
        })
        .catch((error) => console.error(error));
}


function crearCliente(){
    const nombreCliente = document.querySelector("#nombreCliente");
    const nDocCliente = document.querySelector("#nDocCliente");
    const telefonoCliente = document.querySelector("#telefonoCliente");
    const emailCliente = document.querySelector("#emailCliente");
    const direccionCliente = document.querySelector("#direccionCliente");

    const myHeaders = new Headers();
    myHeaders.append("Content-Type", "application/json");
    const raw = JSON.stringify({
        "nombre": nombreCliente.value.trim(),
        "n_documento": nDocCliente.value.trim(),
        "telefono": telefonoCliente.value.trim(),
        "email": emailCliente.value.trim(),
        "direccion": direccionCliente.value.trim()
    });
    const requestOptions = {
    method: "POST",
    headers: myHeaders,
    body: raw,
    redirect: "follow"
    };
    fetch("http://localhost:8080/api/clientes/crear", requestOptions)
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
                <i class="fa-solid fa-pen-circle text-green-600 text-3xl cursor-pointer" onclick="editarCliente('modalCustomer', ${element.id})"></i>
                <i class="fa-solid fa-circle-trash text-red-600 text-3xl cursor-pointer" onclick="confirmarEliminacion('deleteModalC',${element.id})"></i>
            </td>
        </tr>
        `
        listaClientes.push(element);
    })
    .catch((error) => console.error(error));
}

function agregarCliente(modalId){
    openModal(modalId); 
    idCliente.value = ""
    nombreCliente.value = ""
    nDocCliente.value = ""
    telefonoCliente.value = ""
    emailCliente.value = ""
    direccionCliente.value = ""
    tipoAccion.value = "crear"
}

function modificarCliente() {
    const myHeaders = new Headers();
    myHeaders.append("Content-Type", "application/json");

    const raw = JSON.stringify({
        "id": idCliente.value,
        "nombre": nombreCliente.value.trim(),
        "n_documento": nDocCliente.value.trim(),
        "telefono": telefonoCliente.value.trim(),
        "email": emailCliente.value.trim(),
        "direccion": direccionCliente.value.trim()
    });

    const requestOptions = {
        method: "PUT",
        headers: myHeaders,
        body: raw,
        redirect: "follow"
    };

    fetch("http://localhost:8080/api/clientes/actualizar", requestOptions)
        .then((response) => response.json())
        .then(() => {
            listaClientes = [];
            tBody.innerHTML = '';
            obtenerClientes();
        })
        .catch((error) => console.error(error));
}

function editarCliente(modalId, id) {
    openModal(modalId); 
    const cliente = listaClientes.find(element => element.id === id);
    idCliente.value = id;
    nombreCliente.value = cliente.nombre;
    nDocCliente.value = cliente.n_documento;
    telefonoCliente.value = cliente.telefono;
    emailCliente.value = cliente.email;
    direccionCliente.value = cliente.direccion;
    tipoAccion.value = "editar"
}

function confirmarEliminacion(modalId, id){
    openModal(modalId); 
    listaClientes.find(element => element.id === id)
    idCliente.value = id
    tipoAccion.value = "borrar"
}

function eliminarCliente(id){
    const requestOptions = {
        method: "DELETE",
        redirect: "follow"
    };

    fetch(`http://localhost:8080/api/clientes/borrar/${id}`, requestOptions)
        .then((response) => response.text())
        .then(() => {
            listaClientes = []
            tBody.innerHTML = ''
            obtenerClientes()
        })
        .catch((error) => console.error(error));
}


