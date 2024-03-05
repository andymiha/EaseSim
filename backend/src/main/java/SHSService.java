import org.springframework.stereotype.Service;

@Service
public class SHSService {

    // In a real-world application, this information might come from a database or external API
    private int temperature = 0;
    private String weather = "Sunny";
    private String time = "00:00";
    private String date = "2024-02-26";

    public String getSHSInfo() {
        StringBuilder sb = new StringBuilder();
        sb.append("Temperature: ").append(temperature).append("°C\n");
        sb.append("Weather: ").append(weather).append("\n");
        sb.append("Time: ").append(time).append("\n");
        sb.append("Date: ").append(date).append("\n");
        return sb.toString();
    }
}