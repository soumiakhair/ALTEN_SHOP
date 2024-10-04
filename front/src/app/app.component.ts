import {
  Component, HostListener, Output, ViewChild,
} from "@angular/core";
import { RouterModule } from "@angular/router";
import { SplitterModule } from 'primeng/splitter';
import { ToolbarModule } from 'primeng/toolbar';
import { PanelMenuComponent } from "./shared/ui/panel-menu/panel-menu.component";
import {ProductListComponent} from "./products/features/product-list/product-list.component";
import { CommonModule } from '@angular/common';
import {PaniersService} from "./products/data-access/paniers.service";
import {ProductsService} from "./products/data-access/products.service";

@Component({
  selector: "app-root",
  templateUrl: "./app.component.html",
  styleUrls: ["./app.component.scss"],
  standalone: true,
  imports: [RouterModule, SplitterModule, ToolbarModule, PanelMenuComponent,ProductListComponent,CommonModule],
})
export class AppComponent {
  title = "ALTEN SHOP";
  cart:boolean=false;
  productCount: number = 0;



  constructor(private panierService: PaniersService,private productService: ProductsService) {
    this.getProductCount();
  }
  ngOnInit() {

    this.productService.productListUpdated$.subscribe(() => {
      this.onProductListUpdated();
    });

  }

  goToCart(){
   this.cart=true;
  }
  onOptionSelected(){
    this.cart=false;
  }
  onProductListUpdated() {
    this.getProductCount();
  }
  getProductCount() {
    this.panierService.getProductCount().subscribe(
      (count: number) => {
        console.log('Product count:', count);
        this.productCount=count;
      },
      (error: any) => {
        console.error('Error fetching product count:', error);
      }
    );
  }
}
