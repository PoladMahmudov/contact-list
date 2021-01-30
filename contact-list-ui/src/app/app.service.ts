import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders, HttpParams} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Page, User} from './app.model';

@Injectable({
  providedIn: 'root'
})
export class AppService {

  private readonly usersApi = '/api/users';

  constructor(private readonly http: HttpClient) {
  }

  public users(nameCriteria: string, page: number,
               size: number, sort: string, sortDir: string): Observable<Page<User>> {
    const headers = new HttpHeaders({'Content-Type': 'application/json'});
    const params = new HttpParams()
      .set('nameCriteria', nameCriteria)
      .set('page', String(page))
      .set('sort', `${sort},${sortDir}`)
      .set('size', String(size));
    return this.http.get<Page<User>>(this.usersApi, {headers, params});
  }
}
