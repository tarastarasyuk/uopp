import { Http } from './http/httpService';
import { Opportunities } from './opportunities/opportunitiesService';
import { OpportunitiesEditor } from './opportunities/opportunitiesEditorService';

const http = new Http();

const opportunities = new Opportunities({
    baseURL: 'http://localhost:8080',
    http
});

const opportunitiesEditor = new OpportunitiesEditor({
    baseURL: 'http://localhost:8080',
    http
})

export { http, opportunities, opportunitiesEditor };