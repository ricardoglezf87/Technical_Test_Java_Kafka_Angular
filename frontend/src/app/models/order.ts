export interface Order {
  id?: string;
  customer: string;
  product: string;
  quantity: number;
  price: number;
  status?: string;
}