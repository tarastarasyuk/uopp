import { ApiPath, HttpMethod } from 'common/enums';

class TelegramOpportunities {
    constructor({ baseURL, http }) {
        this._baseURL = baseURL;
        this._http = http;
        this._basePath = ApiPath.TELEGRAM;
    }

    getAll(params) {
        const query = !!params.sort ? '?' + this._getQueryString(params) : '';
        return this._http.load(this._getUrl(query), {
            method: HttpMethod.GET,
        });
    }

    _getUrl(path = '') {
        return `${this._baseURL}${this._basePath}${path}`;
    }

    _getQueryString(obj) {
        const keyValuePairs = [];
        for (const key in obj) {
          keyValuePairs.push(encodeURIComponent(key) + '=' + encodeURIComponent(obj[key]));
        }
        return keyValuePairs.join('&');
      }

}

export { TelegramOpportunities };