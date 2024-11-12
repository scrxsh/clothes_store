let listaProveedores = []
const tBody = document.querySelector("#tBody")

obtenerProveedores();

function obtenerProveedores(){
    const requestOptions = {
        method: "GET",
        redirect: "follow"
    };
    
    fetch("http://localhost:8080/api/ventas/todos", requestOptions)
        .then((response) => response.json())
        .then((result) => {
            listaProveedores = result
                result.forEach(element => {
                    tBody.innerHTML +=
                    `
                    <tr class="odd:bg-white even:bg-gray-50 border-b ">
                        <th scope="row" class="px-6 py-4 text-gray-900 whitespace-nowrap">${element.id}</th>
                        <th scope="row" class="px-6 py-4">${element.empleado.nombre}</th>
                        <td class="px-6 py-4">${element.cliente.nombre}</td>
                        <td class="px-6 py-4">${element.fechaVenta}</td>
                        <td class="px-6 py-4">${element.unidades * element.producto.precioFinal}</td>
                        <td class="px-6 py-4 -ml-5 flex space-x-2">
                            <i class="fa-solid fa-file-pdf text-yellow-600 text-3xl cursor-pointer" onclick="obtenerFactura(${element.id})"></i>
                        </td>
                    </tr>
                    `
                });
        })
        .catch((error) => console.error(error));
}


function obtenerFactura(id) {
    const fechaActual = new Date();
    const fechaFormateada = fechaActual.toISOString().replace(/[:.]/g, '-').slice(0, 19);

    const requestOptions = {
        method: "GET",
        redirect: "follow"
    };
    
    fetch(`http://localhost:8080/api/ventas/factura/pdf/${id}`, requestOptions)
    .then((response) => {
        if (!response.ok) {
            throw new Error("Error en la descarga de la factura");
        }
        return response.blob();
    })
    .then((blob) => {
        const url = window.URL.createObjectURL(blob);
        const a = document.createElement("a");
        a.href = url;
        a.download = `F0${id}-${fechaFormateada}.pdf`; 
        document.body.appendChild(a);
        a.click();
        a.remove();
        window.URL.revokeObjectURL(url);
    })
    .catch((error) => console.error("Error al descargar la factura:", error));
}

