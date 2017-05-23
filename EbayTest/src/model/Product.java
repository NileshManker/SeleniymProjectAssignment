package model;

public class Product {

	private String productName ;
	private String price ;
	private String sellerName ;
	private static Product product ;
	
	public Product(){
	}
	


	public Product(String productName, String price, String sellerName) {
		super();
		this.productName = productName;
		this.price = price ;
		this.sellerName = sellerName ;
	}



	public static Product getProductInstance(String productName , String price , String sellerName){
		
		if (product == null) {
		 product =  new Product(productName, price, sellerName)	;
		}	
		return product;
		
	}
	
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getSellerName() {
		return sellerName;
	}
	public void setSellerName(String sellerName) {
		this.sellerName = sellerName;
	}
	
	
	
}
