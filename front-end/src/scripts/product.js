let listProductos = []
const gallery = document.querySelector('#gallery')

obtenerProductos();

function obtenerProductos() {
    const requestOptions = {
        method: "GET",
        redirect: "follow"
        };
        fetch("http://localhost:8080/api/productos/todos", requestOptions)
        .then((response) => response.json())
        .then((result) => {
            listProductos = result
                result.forEach(element => {
                    gallery.innerHTML += 
                    `
                    <clothe-card 
                    urlImg="${element.imgPrenda}"
                    nombrePrenda="${element.nombre}"
                    descripcionPrenda="${element.descripcion}"
                    color="${element.color}"
                    talla="${element.talla}"
                    stock="${element.stock}"
                    precio="${element.precioFinal}"
                    >
                    </clothe-card>
                    `
                });
        })
        .catch((error) => console.error(error));
}
