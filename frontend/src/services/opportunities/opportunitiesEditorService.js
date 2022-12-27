import { ApiPath, HttpMethod } from 'common/enums';

class OpportunitiesEditor {
    constructor({ baseURL, http }) {
        this._baseURL = baseURL;
        this._http = http;
        this._basePath = ApiPath.OPPORTUNITIES_EDITOR;
    }

    getAll(){
        return this._http.load(this._getUrl(), {
            method: HttpMethod.GET,
        });
    }
    
    create(path, payload){
        return this._http.load(this._getUrl(path), {
            method: HttpMethod.POST,
            payload: JSON.stringify(payload),
            contentType: 'application/json',
        })
    }

    edit(path, id, payload){
        const url = path + '/' + id;
        return this._http.load(this._getUrl(url), {
            method: HttpMethod.PUT,
            payload: JSON.stringify(payload),
            contentType: 'application/json',
        });
    }

    delete(path, id){
        const url = path + '/' + id;
        return this._http.load(this._getUrl(url), {
            method: HttpMethod.DELETE,
            contentType: 'application/json'
        });
    }

    _getUrl(path = '') {
        return `${this._baseURL}${this._basePath}${path}`;
    }

}

export { OpportunitiesEditor };