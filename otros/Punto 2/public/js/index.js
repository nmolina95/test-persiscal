/* Aclaraciones: El código se construyo de la siguiente forma para que en caso de ser necesario otro parámetro, se pueda
 * agregar el mismo y realizar la búsqueda sin problema.

 * Actualmente el servicio externo utilizado está solo pensado para el parámetro "direccion", pero si hubiese otro como 
 * "barrio", mientras el formato de la respuesta sea el mismo, el código puede incluirlo en la petición y devolver la respuesta
 * basada en la "direccion" y "nombre_calle" sin necesidad de saber la nueva key y value.
 *  
 * Esto se logra con la función getUrl, que se encarga de revisar todos los parámetros recibidos e incluirlos en la url de la
 * cual traeremos datos.
 * 
 * Luego de invocar a la función fetchDataFromAPI y obtener su respuesta, con mapData se extrae de estos los datos "direccion" 
 * y "nombre_calle" y se arma la respuesta requerida en la consigna, que luego se carga en el html mediante showData()
 */

const baseUrl = "https://servicios.usig.buenosaires.gob.ar/normalizar";

document.addEventListener("DOMContentLoaded",() => {
    const urlParams = new URLSearchParams(window.location.search);

    fetchDataFromAPI(baseUrl, urlParams)
        .then(data => {
            const mappedData = mapData(data);
            showData(mappedData);
        })
        .catch(err => {
            console.error("Error en la búsqueda: ", err);
        });
})

function getUrl(baseUrl, params) {
    const url = new URL(baseUrl);
    
    for(const [key, param] of params.entries()) {
        url.searchParams.append(key, param);
    }

    return url;
}

async function fetchDataFromAPI(baseUrl, params) {
    const url = getUrl(baseUrl, params);

    try {
        const response = await fetch(url);

        if(!response.ok) {
            throw new Error("Error en la solicitud: ${response.status}")
        }

        const data = await response.json();
        return data;
    } catch (err) {
        console.error("Error al realizar la solicitud: ", err);
        return null;
    }
}

function mapData(data) {
    const mappedData = [];
    data = Object.values(data)[0];

    data.forEach(element => {
        const mappedResult = {
            direccion: element.direccion,
            nombre_calle: element.nombre_calle
        };

        mappedData.push(mappedResult);
    });

    return mappedData;
}

function showData(data) {
    const resultsContainer = document.getElementById("resultsContainer");

    data.forEach(register => {
        Object.entries(register).forEach(([key, value]) => {
            const element = document.createElement('p');
            element.innerHTML += `${key}: ${value}`;
            resultsContainer.appendChild(element);
        })
    })
}