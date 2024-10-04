import {inject, Injectable, signal} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Product} from "./product.model";
import { catchError, Observable, of, tap } from 'rxjs';
import {EMPTY, map} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class PaniersService {

  private readonly http = inject(HttpClient);
  private  path = "http://localhost:8080/api/paniers";

  private readonly _products = signal<Product[]>([]);

  public readonly products = this._products.asReadonly();

  public get(): Observable<Product[]> {
    return this.http.get<Product[]>(this.path).pipe(
      catchError((error) => {
        console.error('Error fetching products:', error);
        return EMPTY;
      }),
      tap((products) => this._products.set(products)),
    );
  }

  public addToPanier(product: Product): Observable<boolean> {
    return this.http.post<boolean>(this.path, product).pipe(
      catchError(() => {
        return of(true);
      }),
      tap(() => this._products.update(products => [product, ...products])),
    );
  }



  public removeFromPanier(productId: number): Observable<boolean> {
    return this.http.delete<boolean>(`${this.path}/${productId}`).pipe(
      catchError(() => {
        return of(true);
      }),
      tap(() => this._products.update(products => products.filter(product => product.id !== productId))),
    );
  }

  public getProductCount(): Observable<number> {
    return this.http.get<Product[]>(this.path).pipe(
      catchError((error) => {
        console.error('Error fetching products:', error);
        return EMPTY;
      }),
      tap((products) => this._products.set(products)),
      map((products) => products.length)
    );
  }
}
