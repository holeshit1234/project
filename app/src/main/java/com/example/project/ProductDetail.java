    package com.example.project;

    import android.content.Intent;
    import android.database.Cursor;
    import android.os.Bundle;
    import android.view.View;
    import android.widget.Button;
    import android.widget.ImageView;
    import android.widget.ListView;
    import android.widget.TextView;
    import android.widget.Toast;

    import androidx.appcompat.app.AppCompatActivity;

    import com.example.project.adapter.ClothesAdapter;
    import com.example.project.database.Database;
    import com.example.project.model.Cart;
    import com.example.project.model.Clothes;

    import java.util.ArrayList;

    public class ProductDetail extends AppCompatActivity {


        TextView name ;
        TextView details ;
        TextView price ;
        TextView quantity ;
        ImageView image ;


        Database database;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_product_detail);

            name = findViewById(R.id.nameClothes);
             details = findViewById(R.id.detailClothes);
             price = findViewById(R.id.priceClothes);
             quantity = findViewById(R.id.quantityClothes);
            image = findViewById(R.id.imageClothes);
            int productId = getIntent().getIntExtra("product_id", -1);





            // Initialize the database helper
            database = new Database(this, "User.db", null, 1);

            String query = "SELECT * FROM Clothes WHERE id = " + productId;
            Cursor dataClothes = database.GetData(query);
            // Lấy thông tin sản phẩm từ cơ sở dữ liệu
            if (dataClothes.moveToNext()) {
                int id = dataClothes.getInt(0);
                String nameClothes = dataClothes.getString(1);
                String detailsClothes = dataClothes.getString(2);
                int imageClothes = dataClothes.getInt(3);
                int quantityClothes = dataClothes.getInt(4);
                double priceClothes = dataClothes.getDouble(5);

                // Gắn thông tin sản phẩm vào các TextView và ImageView
                name.setText(nameClothes);
                details.setText(detailsClothes);
                price.setText("Price: $" + priceClothes); // Định dạng giá tiền theo đơn vị đô la
                quantity.setText("Quantity: " + quantityClothes);
                image.setImageResource(imageClothes); // Đặt hình ảnh sản phẩm

            }




            ImageView homeListClothes = findViewById(R.id.home);
            ImageView cartClothes = findViewById(R.id.cart);

            homeListClothes.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Xử lý sự kiện khi nhấp vào biểu tượng imageClothes (quay lại trang trước)
                    onBackPressed(); // Quay lại trang trước
                }
            });

            cartClothes.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Xử lý sự kiện khi nhấp vào biểu tượng shoppingCartIcon (chuyển đến trang Cart)
                    Intent intent = new Intent(ProductDetail.this, CartActivity.class);
                    startActivity(intent);
                }
            });




            Button btnAddToCart = findViewById(R.id.btnAddToCart);

            btnAddToCart.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Thêm sản phẩm vào bảng Cart




    // Lấy chuỗi từ TextView
                    String priceText = price.getText().toString();

    // Loại bỏ phần tiền tệ và khoảng trắng
                    priceText = priceText.replace("Price: $", "").trim();

    // Chuyển đổi chuỗi thành giá trị double
                    double priceValue = Double.parseDouble(priceText);
                    String checkProductName = name.getText().toString();
                    // Kiểm tra xem sản phẩm đã tồn tại trong giỏ hàng hay không
                    if (database.checkProductExist(checkProductName)) {
                        // Nếu sản phẩm đã tồn tại, tăng số lượng lên 1 đơn vị và giá

                        String query = "SELECT Quantity, Price FROM Cart WHERE Name = '" + checkProductName + "'";
                        Cursor dataCart = database.GetData(query);

                        if (dataCart != null && dataCart.moveToFirst()) {
                            int currentQuantity = dataCart.getInt(0);
                            double currentPrice = dataCart.getDouble(1);
                            boolean success = database.updateCartItemQuantity(checkProductName, currentQuantity + 1, currentPrice + priceValue);
                            if (success) {
                                Toast.makeText(ProductDetail.this, "Quantity increased", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(ProductDetail.this, "Failed to increase quantity", Toast.LENGTH_SHORT).show();
                            }
                        }

                    } else {
                        // Nếu sản phẩm chưa tồn tại, thêm sản phẩm vào giỏ hàng với số lượng là 1
                        String query = "SELECT Image FROM Clothes WHERE id = " + productId;
                        Cursor dataClothes = database.GetData(query);
                        if (dataClothes != null && dataClothes.moveToFirst()) {
                            int imageValue = dataClothes.getInt(0);

                            boolean success = database.insertToCart( checkProductName, priceValue, 1, imageValue);
                            if (success) {
                                Toast.makeText(ProductDetail.this, "Added to Cart", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(ProductDetail.this, "Failed to add to Cart", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                }
            });




        }



    }
