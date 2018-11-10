import {Directive, ElementRef, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {fromEvent} from 'rxjs';
import {NgModel} from '@angular/forms';
import {debounceTime, filter, map} from "rxjs/operators";

@Directive({selector: '[debounce]'})
export class DebounceDirective implements OnInit {
  @Input() delay: number = 700;
  @Output() func: EventEmitter<any> = new EventEmitter();

  constructor(private elementRef: ElementRef, private model: NgModel) {
  }

  ngOnInit(): void {
    let previousValue;
    const eventStream = fromEvent(this.elementRef.nativeElement, 'keyup').pipe(
      map(() => this.model.value),
      filter((value) => value !== previousValue),
      debounceTime(this.delay)
    );
    eventStream.subscribe(input => {
      previousValue = input;
      this.func.emit(input)
    });
  }

}
