import { Component } from '@angular/core';
import {ContactService} from "../data-access/contact.service";
import { HttpErrorResponse } from '@angular/common/http';

import { FormsModule } from '@angular/forms';


@Component({
  selector: 'app-contact-form',
  standalone: true,
  imports: [FormsModule],
  templateUrl: './contact-form.component.html',
  styleUrl: './contact-form.component.css'
})
export class ContactFormComponent {
  email: string = '';
  message: string = '';

  constructor(private contactService: ContactService) {}

  submitForm() {
    if (this.message.length < 300) {
      this.contactService.sendContactForm(this.email, this.message)
        .subscribe(
          (response: string) => {
            console.log('Success:', response);
            alert('Demande de contact envoyée avec succès');
          },
          (error: HttpErrorResponse) => {
            console.error('Error:', error);
            alert('Probleme d\'envoi de contact');
          }
        );
    }else{
      alert('message doit être inférieur à 300 caractères.');
    }
  }
}
