import { CommonModule } from '@angular/common';
import { Component, input } from '@angular/core';
import { Order } from '../../models/order';

@Component({
  imports: [CommonModule],
  selector: 'app-order-list',
  styleUrl: './order-list.css',
  templateUrl: './order-list.html',
})
export class OrderList {
  
  orders = input<Order[]>([]);

}
