import {AfterViewInit, Component, ViewChild} from '@angular/core';
import {MatPaginator, PageEvent} from '@angular/material/paginator';
import {MatSort, Sort} from '@angular/material/sort';
import {MatTableDataSource} from '@angular/material/table';
import {User} from './app.model';
import {AppService} from './app.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements AfterViewInit {

  nameCriteria = '';
  displayedColumns: string[] = ['avatarUrl', 'name'];
  dataSource = new MatTableDataSource<User>([]);

  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;

  constructor(private readonly appService: AppService) {
  }

  ngAfterViewInit(): void {
    this.getUsers();
  }

  applyFilter(event: Event): void {
    this.nameCriteria = (event.target as HTMLInputElement).value;
    this.getUsers(this.nameCriteria, 0, this.paginator.pageSize);
  }

  handleSortEvent(sort: Sort): void {
    if (!sort.active || sort.direction === '') {
      this.getUsers();
    } else {
      this.getUsers(this.nameCriteria, this.paginator.pageIndex, this.paginator.pageSize, sort.active, sort.direction);
    }
  }

  handlePageEvent(event: PageEvent): void {
    this.getUsers(this.nameCriteria, event.pageIndex, event.pageSize);
  }

  private getUsers(criteria: string = this.nameCriteria,
                   page: number = this.paginator.pageIndex,
                   size: number = this.paginator.pageSize,
                   sort: string = this.sort.active || 'name',
                   sortDir: string = this.sort.direction || 'asc'): void {
    this.appService.users(criteria, page, size, sort, sortDir)
      .subscribe(usersPage => {
        this.dataSource.data = usersPage.content;
        this.paginator.pageIndex = usersPage.number;
        this.paginator.pageSize = usersPage.size;
        this.paginator.length = usersPage.totalElements;
      });
  }
}
