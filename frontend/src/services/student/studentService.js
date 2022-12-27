import { ApiPath, HttpMethod } from 'common/enums';

class Student {
    constructor({ baseURL, http }) {
        this._baseURL = baseURL;
        this._http = http;
        this._basePath = ApiPath.STUDENT;
    }

    create(path, payload){
        return this._http.load(this._getUrl(path), {
            method: HttpMethod.POST,
            payload: JSON.stringify(payload),
            contentType: 'application/json',
        })
    }

    getStudent(path, payload){
        return this._http.load(this._getUrl(path), {
            method: HttpMethod.POST,
            payload: JSON.stringify(payload),
            contentType: 'application/json',
        })
    }

    _getUrl(path = '') {
        return `${this._baseURL}${this._basePath}${path}`;
    }

}

export { Student };