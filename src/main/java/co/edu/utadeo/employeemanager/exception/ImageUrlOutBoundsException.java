package co.edu.utadeo.employeemanager.exception;

public class ImageUrlOutBoundsException extends
        RuntimeException {

    public ImageUrlOutBoundsException(String imageUrl) {
        super(imageUrl);
    }
}
