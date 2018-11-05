import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup} from "@angular/forms";
import {CreateConsignmentNoteDto} from "../dto/create-consignment-note-dto";

@Component({
  selector: 'app-register-consignment-note',
  templateUrl: './register-consignment-note.component.html',
  styleUrls: ['./register-consignment-note.component.css']
})
export class RegisterConsignmentNoteComponent implements OnInit {
  consignmentNoteForm: FormGroup;
  consignmentNote: CreateConsignmentNoteDto;

  constructor(private fb: FormBuilder) {
    this.consignmentNoteForm = fb.group({
      "number": [''],
      "shipment": ['']
    });
  }

  ngOnInit() {
  }

  onSubmit(consignmentNoteForm: FormGroup): void {
    console.log(this.consignmentNoteForm.value);
    consignmentNoteForm.reset();
  }
}
