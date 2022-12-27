import { ApiPath, HttpMethod } from 'common/enums';

class Opportunities {
    constructor({ baseURL, http }) {
        this._baseURL = baseURL;
        this._http = http;
        this._basePath = ApiPath.OPPORTUNITIES;
        this._profile = ApiPath.PROFILE;
    }

    getAll() {
        return this._http.load(this._getUrl(), {
            method: HttpMethod.GET,
        });
    }

    _getUrl(path = '') {
        return `${this._baseURL}${this._basePath}${path}`;
    }

}

export { Opportunities };