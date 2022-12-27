import { Http } from './http/httpService';
import { Opportunities } from './opportunities/opportunitiesService';
import { OpportunitiesEditor } from './opportunities/opportunitiesEditorService';
import { Student } from './student/studentService';
import { Profile } from './profile/profileService';

const http = new Http();

const opportunities = new Opportunities({
    baseURL: 'http://localhost:8080',
    http
});

const opportunitiesEditor = new OpportunitiesEditor({
    baseURL: 'http://localhost:8080',
    http
})

const student = new Student({
    baseURL: 'http://localhost:8080',
    http
})

const profile = new Profile({
    baseURL: 'http://localhost:8080',
    http
})

export { http, opportunities, opportunitiesEditor, student, profile };