import { ActionType } from 'store/profile/common';
import { ApiPath, HttpMethod } from 'common/enums';

class Profile {
    constructor({ baseURL, http }) {
        this._baseURL = baseURL;
        this._http = http;
        this._basePath = ApiPath.PROFILE;
    }

    getProfile(id) {
        return this._http.load(this._getUrl(id), {
            method: HttpMethod.GET,
        });
    }

    editProfile(id, payload) {
        return this._http.load(this._getEditUrl(id), {
            method: HttpMethod.PUT,
            payload: JSON.stringify(payload),
            contentType: 'application/json',
        });
    }

    likeOpportunity(path, student, opportunity) {
        const url = `/${student}${path}/${opportunity}`;
        return this._http.load(this._getLikedUrl(url), {
            method: HttpMethod.PUT,
        })
    }

    unlikeOpportunity(path, student, opportunity) {
        const url = `/${student}${path}/${opportunity}`;
        return this._http.load(this._getLikedUrl(url), {
            method: HttpMethod.PUT,
        })
    }

    _getUrl(path = '') {
        return `${this._baseURL}${this._basePath}/${path}`;
    }

    _getEditUrl(path = '') {
        return `${this._baseURL}${this._basePath}/${path}${ActionType.SAVE}`;
    }

    _getLikedUrl(path = '') {
        return `${this._baseURL}${ApiPath.OPPORTUNITIES}${this._basePath}${path}`;
    }
}

export { Profile };