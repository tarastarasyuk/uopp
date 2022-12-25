import { ApiPath, HttpMethod } from 'common/enums';

class Opportunities {
    constructor({ baseURL, http }) {
        this._baseURL = baseURL;
        this._http = http;
        this._basePath = ApiPath.OPPORTUNITIES;
    }

    getAll(){
        return this._http.load(this._getUrl(), {
            method: HttpMethod.GET,
        });
    }

    particalUpdate(id, payload){
        return this._http.load(this._getUrl(id), {
            method: HttpMethod.PATCH,
            payload: JSON.stringify(payload),
            contentType: 'application/json',
        });
    }

    _getUrl(path = '') {
        return `${this._baseURL}${this._basePath}/${path}`;
    }

}

export { Opportunities };