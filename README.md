"# Android_Realm_Database" 
Giới thiệu

Realm là một cơ sở dữ liệu nhẹ, có thể thay thế cả hai thư viện SQL và ORM trong các ứng dụng Android. Realm không sử dụng SQLite làm engine của nó. Thay vào đó, nó dùng core C++ nhằm mục đích cung cấp một thư viện cơ sở dữ liệu thay thế SQLite.

Realm lưu trữ dữ liệu trong các bảng viết bằng core C++. Việc này cho phép Realm được truy cập dữ liệu từ nhiều ngôn ngữ cũng như một loạt các truy vấn khác nhau.

Dưới đây là những ưu điểm của Realm so với SQLite:

Nhanh hơn so với SQLite (gấp 10 lần so với SQLite cho các hoạt động bình thường).
Dễ sử dụng.
Chuyển đổi đối tượng xử lý cho bạn.
Thuận tiện cho việc tạo ra và lưu trữ dữ liệu nhanh chóng.
Ngoài ra còn có một số nhược điểm sau :

Vẫn còn đang phát triển.
Không có nhiều kênh trao đổi trực tuyến.
Không thể truy cập các đối tượng thông qua thread.

1. Add Realm vào Project

Để sử dụng Realm trong Proejct, bạn cần compile Realm trong file build.gradle

compile 'io.realm:realm-android:0.84.1'