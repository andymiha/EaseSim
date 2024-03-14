// SHSController.java

public class SHSController {
    private final SHSService shsService;

    public SHSController(SHSService shsService) {
        this.shsService = shsService;
    }

    public String getData(String userId) {
        String data = shsService.getData(userId);
        // Convert data to JSON or any desired format
        return data;
    }

    public void saveData(String data) {
        shsService.saveData(data);
    }

    public void updateData(String data) {
        shsService.updateData(data);
    }

    public void deleteData(String dataId) {
        shsService.deleteData(dataId);
    }
}
