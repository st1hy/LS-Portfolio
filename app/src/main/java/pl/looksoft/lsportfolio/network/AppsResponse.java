package pl.looksoft.lsportfolio.network;

import java.util.List;

public class AppsResponse {
    int status;
    Data data;

    public int getStatus() {
        return status;
    }

    public Data getData() {
        return data;
    }

    @Override
    public String toString() {
        return "AppsResponse{" +
                "status=" + status +
                ", data=" + data +
                '}';
    }

    public static class Data {
        List<App> portfolio;

        public List<App> getPortfolio() {
            return portfolio;
        }

        @Override
        public String toString() {
            return "Data{" +
                    "portfolio=" + portfolio +
                    '}';
        }
    }

    public static class App {
        int id;
        String name;
        String description;
        String icon;
        String link;

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public String getDescription() {
            return description;
        }

        public String getIcon() {
            return icon;
        }

        public String getLink() {
            return link;
        }

        @Override
        public String toString() {
            return "App{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    ", description='" + description + '\'' +
                    ", icon='" + icon + '\'' +
                    ", link='" + link + '\'' +
                    '}';
        }
    }
}
