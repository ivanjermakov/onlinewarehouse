import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {BehaviorSubject, of} from "rxjs";
import {Page} from "../../shared/pagination/page";
import {UserDto} from "../dto/user.dto";
import {Pageable} from "../../shared/pagination/pageable";
import {UserService} from "../service/user.service";
import {PageEvent} from "@angular/material";
import {catchError, finalize} from "rxjs/operators";

@Component({
  selector: 'app-user-list',
  templateUrl: './user-list.component.html',
  styleUrls: ['./user-list.component.css']
})
export class UserListComponent implements OnInit {

  @Output() userSelected: EventEmitter<UserDto> = new EventEmitter();

  private displayedColumns = ["id", "username", "name", "address", "birth", "enabled", "authorities"];
  private loadingSubject = new BehaviorSubject<boolean>(false);
  loading$ = this.loadingSubject.asObservable();
  private page: Page<UserDto>;

  private pageable: Pageable = new Pageable(0, 10);
  private pageSizeOptions: number[] = [10, 25, 50];

  private errors: any[];

  constructor(private userService: UserService) {
  }

  ngOnInit() {
    this.loadUsers();
  }

  pageChanged(event: PageEvent) {
    this.page = null;
    this.pageable = new Pageable(event.pageIndex, event.pageSize);
    this.loadUsers();
  }

  onRowClicked(row): void {
    this.userSelected.emit(row);
  }

  loadUsers(): void {
    this.loadingSubject.next(true);
    this.userService.getAllUsers(this.pageable, 2).pipe(
      catchError(() => of([])),
      finalize(() => this.loadingSubject.next(false))
    )
      .subscribe(page => {
        if (page instanceof Array) {
          this.errors = page as any[];
        } else {
          this.page = page;
        }
      });
  }

}
