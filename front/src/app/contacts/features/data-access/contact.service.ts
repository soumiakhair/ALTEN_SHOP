import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import { Observable } from 'rxjs';


interface ContactForm {
  email: string;
  message: string;
}

@Injectable({
  providedIn: 'root'
})
export class ContactService {


  private apiUrl = 'http://localhost:8080/api/contact';
  constructor(private http: HttpClient) { }

  sendContactForm(email: string, message: string): Observable<string> {
    const headers = new HttpHeaders({ 'Content-Type': 'application/json' });
    const body: ContactForm = { email, message };

    return this.http.post<string>(this.apiUrl, body, { headers });
  }
}
