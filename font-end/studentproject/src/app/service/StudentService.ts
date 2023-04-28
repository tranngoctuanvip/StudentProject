import{HttpClient} from '@angular/common/http';
import { student } from '../model/student';
import { Observable } from 'rxjs';
import { Injectable } from '@angular/core';

@Injectable({
    providedIn : 'root'
})
export class StudentService{
    private API_URL = 'http://localhost:8088/student/search?code=&name=&sex=&size=';
    message!: string;

  constructor(private httpClient: HttpClient) {
    console.log('service constructor');
  }

  findAll(): Observable<student[]> {
    return this.httpClient.get<student[]>(this.API_URL);
  }

  save(student: student): Observable<void> {
    return this.httpClient.post<void>(this.API_URL, student);
  }
}