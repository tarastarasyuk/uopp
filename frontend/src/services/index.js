import { Http } from './http/httpService';
import { Opportunities } from './opportunities/opportunitiesService';

const http = new Http();

const opportunities = new Opportunities({
    baseURL: 'http://localhost:8080',
    http
});

export { http, opportunities };