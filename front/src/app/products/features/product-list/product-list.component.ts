import {Component, OnInit, inject, signal, Input, EventEmitter, Output, HostListener} from "@angular/core";
import { Product } from "app/products/data-access/product.model";
import { ProductsService } from "app/products/data-access/products.service";
import { ProductFormComponent } from "app/products/ui/product-form/product-form.component";
import { ButtonModule } from "primeng/button";
import { CardModule } from "primeng/card";
import { DataViewModule } from 'primeng/dataview';
import { DialogModule } from 'primeng/dialog';
import {PaniersService} from "../../data-access/paniers.service";
import { CommonModule } from '@angular/common';
import {Subject} from "rxjs";

const emptyProduct: Product = {
  id: 0,
  code: "",
  name: "",
  description: "",
  image: "",
  category: "",
  price: 0,
  quantity: 0,
  internalReference: "",
  shellId: 0,
  inventoryStatus: "INSTOCK",
  rating: 0,
  createdAt: 0,
  updatedAt: 0,
};

@Component({
  selector: "app-product-list",
  templateUrl: "./product-list.component.html",
  styleUrls: ["./product-list.component.scss"],
  standalone: true,
  imports: [DataViewModule, CardModule, ButtonModule, DialogModule, ProductFormComponent,CommonModule],
})
export class ProductListComponent implements OnInit {
  @Input()
  public  isPanier:boolean=false;
  private readonly productsService = inject(ProductsService);
  private readonly panierService = inject(PaniersService);

  public readonly products = this.productsService.products;
  public readonly paniers = this.panierService.products;

  public isDialogVisible = false;
  public isCreation = false;
  public readonly editedProduct = signal<Product>(emptyProduct);
  @Output() listUpdated = new EventEmitter<void>();


  private productListUpdated = new Subject<void>();
  productListUpdated$ = this.productListUpdated.asObservable();

  ngOnInit() {
    console.log(this.isPanier);
    if (!this.isPanier) {
      this.productsService.get().subscribe();
    }else {
      this.panierService.get().subscribe();
    }
  }

  public onCreate() {
    this.isCreation = true;
    this.isDialogVisible = true;
    this.editedProduct.set(emptyProduct);

  }

  public onUpdate(product: Product) {
    this.isCreation = false;
    this.isDialogVisible = true;
    this.editedProduct.set(product);
  }

  public onDelete(product: Product) {
    this.productsService.delete(product.id).subscribe();
  }

  public onSave(product: Product) {
    if (this.isCreation) {
      this.productsService.create(product).subscribe();
    } else {
      this.productsService.update(product).subscribe();
    }
    this.closeDialog();
  }

  public onCancel() {
    this.closeDialog();
  }

  private closeDialog() {
    this.isDialogVisible = false;
  }
  addToCart(product:any){
    this.panierService.addToPanier(product).subscribe(
      () =>{ this.productsService.triggerUpdate();
      }
      );

  }
  retirer(product:Product){
    this.panierService.removeFromPanier(product.id).subscribe(
      () =>{ this.listUpdated.emit();
      }
    );
  }
}
