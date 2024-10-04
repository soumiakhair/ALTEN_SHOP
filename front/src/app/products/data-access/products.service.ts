import { Injectable, inject, signal } from "@angular/core";
import { Product } from "./product.model";
import { HttpClient } from "@angular/common/http";
import { catchError, Observable, of, tap } from "rxjs";
import {switchMap} from "rxjs/operators";
import {Subject} from "rxjs";

@Injectable({
    providedIn: "root"
}) export class ProductsService {

    private readonly http = inject(HttpClient);
    private  path = "http://localhost:8080/api/products";

    private readonly _products = signal<Product[]>([]);

    public readonly products = this._products.asReadonly();

  private productListUpdated = new Subject<void>();

  productListUpdated$ = this.productListUpdated.asObservable();

  triggerUpdate() {
    this.productListUpdated.next();
  }

    public get(): Observable<Product[]> {
        return this.http.get<Product[]>(this.path).pipe(
          switchMap((products) => {
          if (products && products.length > 0) {
          return of(products);
          } else {
          return this.http.get<Product[]>("assets/products.json");
         }
           }),
            catchError((error) => {
                return this.http.get<Product[]>("assets/products.json");
            }),
            tap((products) => this._products.set(products)),
        );
    }

    public create(product: Product): Observable<boolean> {
        return this.http.post<boolean>(this.path, product).pipe(
            catchError(() => {
                return of(true);
            }),
            tap(() => this._products.update(products => [product, ...products])),
        );
    }

    public update(product: Product): Observable<boolean> {
        return this.http.patch<boolean>(`${this.path}/${product.id}`, product).pipe(
            catchError(() => {
                return of(true);
            }),
            tap(() => this._products.update(products => {
                return products.map(p => p.id === product.id ? product : p)
            })),
        );
    }

    public delete(productId: number): Observable<boolean> {
        return this.http.delete<boolean>(`${this.path}/${productId}`).pipe(
            catchError(() => {
                return of(true);
            }),
            tap(() => this._products.update(products => products.filter(product => product.id !== productId))),
        );
    }
}
