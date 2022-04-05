// about async: https://developer.mozilla.org/zh-CN/docs/Web/JavaScript/Reference/Statements/async_function

async function getcountries(s,p) {

let cnt = 0;
let pages;

// about axios: abhttps://axios-http.com/docs/intro
const axios = require('axios');

//about await https://developer.mozilla.org/zh-CN/docs/Web/JavaScript/Reference/Operators/await
const response = await axios.get('https://jsonmock.hackerrank.com/api/countries/search?name=' + s + '&page='+1);

    const load = response.data;
    pages = load.total_pages;

    while (pages > 0) {
        const response = await axios.get('https://jsonmock.hackerrank.com/api/countries/search?name=' + s + '&page='+pages);
        const payload = response.data;
        const countries = payload.data;

        for (let country of countries) {
            let population = country.population;
            if (population > p) {
            cnt += 1;
            }
        }
        pages -= 1;
        if (pages == 0) {
            console.log(cnt);
        }
    
    }
}

getcountries('co',5);