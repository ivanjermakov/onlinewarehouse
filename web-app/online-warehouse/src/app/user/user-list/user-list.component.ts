import {Component, OnInit} from '@angular/core';
import {BehaviorSubject} from "rxjs";
import {Page} from "../../shared/pagination/page";
import {UserDto} from "../dto/user.dto";
import {Pageable} from "../../shared/pagination/pageable";
import {UserService} from "../service/user.service";
import {PageEvent} from "@angular/material";
import {debounceTime, distinctUntilChanged, finalize, tap} from "rxjs/operators";
import {FormBuilder, FormGroup} from "@angular/forms";
import {UserFilter} from "../dto/user-filter";
import {Router} from "@angular/router";
import {AuthorityNameEnum} from "../dto/enum/authority-name.enum";
import {RequestErrorToastHandlerService} from "../../shared/toast/request-error-handler/request-error-toast-handler.service";

@Component({
  selector: 'app-user-list',
  templateUrl: './user-list.component.html',
  styleUrls: ['./user-list.component.css']
})
export class UserListComponent implements OnInit {

  private displayedColumns = ["username", "name", "address", "birth", "enabled", "authorities"];
  private loadingSubject = new BehaviorSubject<boolean>(false);
  loading$ = this.loadingSubject.asObservable();
  private pageable: Pageable = new Pageable(0, 10);
  private pageSizeOptions: number[] = [10, 25, 50];

  private page: Page<UserDto>;
  private userFilterForm: FormGroup;
  private userFilter: UserFilter = new UserFilter();

  private authorityNameEnum = AuthorityNameEnum;

  constructor(private userService: UserService,
              private fb: FormBuilder,
              private router: Router,
              private errorToast: RequestErrorToastHandlerService) {
    this.userFilterForm = fb.group({
      "firstname": [''],
      "lastname": ['']
    });
  }

  ngOnInit(): void {
    this.loadUsers();
    this.userFilterForm.valueChanges.pipe(debounceTime(500),
      distinctUntilChanged(),
      tap(() => {
        this.page = null;
        this.pageable.page = 0;
        let value = this.userFilterForm.value;
        Object.assign(this.userFilter, value);
        this.loadUsers();
      })
    ).subscribe();
  }

  pageChanged(event: PageEvent) {
    this.page = null;
    this.pageable = new Pageable(event.pageIndex, event.pageSize);
    this.loadUsers();
  }

  onRowClicked(row: UserDto): void {
    this.router.navigate([`app/user/${row.id}`]);
  }

  loadUsers(): void {
    this.loadingSubject.next(true);
    this.userService.getAllUsers(this.userFilter.toServerFilter(), this.pageable).pipe(
      finalize(() => this.loadingSubject.next(false))
    )
      .subscribe((page) => {
          this.page = page;
        }, (err: any) => {
          console.log('test');
          this.errorToast.handleError(err);
        }
      );
  }

}
