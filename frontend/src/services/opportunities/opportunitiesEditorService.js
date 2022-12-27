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
        this._basePath += path;
        return this._http.load(this._getUrl(id), {
            method: HttpMethod.PUT,
            payload: JSON.stringify(payload),
            contentType: 'application/json',
        });
    }

    delete(path, id){
        this._basePath += path;
        return this._http.load(this._getUrl(id), {
            method: HttpMethod.DELETE,
            contentType: 'application/json'
        });
    }

    _getUrl(path = '') {
        return `${this._baseURL}${this._basePath}${path}`;
    }

}

export { OpportunitiesEditor };