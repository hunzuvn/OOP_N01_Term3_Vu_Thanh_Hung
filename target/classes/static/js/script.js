// src/main/resources/static/js/script.js

document.addEventListener('DOMContentLoaded', function() {
    // 1. Tự động ẩn thông báo sau một thời gian
    const messages = document.querySelectorAll('.message');
    if (messages.length > 0) {
        messages.forEach(message => {
            setTimeout(() => {
                message.style.opacity = '0';
                message.style.transition = 'opacity 1s ease-out';
                // Sau khi mờ đi, loại bỏ khỏi DOM để không chiếm không gian
                message.addEventListener('transitionend', () => message.remove());
            }, 5000); // Ẩn sau 5 giây (5000 milliseconds)
        });
    }

    // 2. Hiệu ứng focus cho input/textarea/select (đã được xử lý phần lớn bởi CSS, nhưng JS có thể thêm tương tác phức tạp hơn nếu cần)
    const formInputs = document.querySelectorAll('input[type="text"], input[type="number"], input[type="email"], input[type="password"], textarea, select');
    formInputs.forEach(input => {
        input.addEventListener('focus', function() {
            this.classList.add('is-focused');
        });
        input.addEventListener('blur', function() {
            this.classList.remove('is-focused');
        });
    });

    // 3. Hiệu ứng cuộn mượt mà cho các liên kết nội bộ (ví dụ: nếu có thanh điều hướng cuộn đến phần nào đó của trang)
    document.querySelectorAll('a[href^="#"]').forEach(anchor => {
        anchor.addEventListener('click', function (e) {
            e.preventDefault();

            document.querySelector(this.getAttribute('href')).scrollIntoView({
                behavior: 'smooth'
            });
        });
    });

});