import { ApiPath, HttpMethod } from 'common/enums';

class OpportunitiesEditor {
    constructor({ baseURL, http }) {
        this._baseURL = baseURL;
        this._http = http;
        this._basePath = ApiPath.OPPORTUNITIES_EDITOR;
        this._telegramPath = ApiPath.TELEGRAM;

    }

    getAll(params) {
        const query = !!params.sort ? '?' + this._getQueryString(params) : '';
        return this._http.load(this._getUrl(query), {
            method: HttpMethod.GET,
        });
    }

    getAllFromTelegram(){
        return this._http.load(this._getTelegramUrl(), {
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

    _getTelegramUrl(path = '') {
        return `${this._baseURL}${this._telegramPath}${path}`;
    }

    _getQueryString(obj) {
        const keyValuePairs = [];
        for (const key in obj) {
          keyValuePairs.push(encodeURIComponent(key) + '=' + encodeURIComponent(obj[key]));
        }
        return keyValuePairs.join('&');
      }

}

export { OpportunitiesEditor };