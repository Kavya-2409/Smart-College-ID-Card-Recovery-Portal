    private static List<FoundReport> searchFoundReports(String query, String searchType) {
        List<FoundReport> list = new ArrayList<>();
        String q = query.toLowerCase();
        for (FoundReport fr : memFoundReports) {
            if ("roll".equalsIgnoreCase(searchType)) {
                if (fr.getRollNumber() != null && fr.getRollNumber().toLowerCase().contains(q)) list.add(fr);
            } else {
                if (fr.getStudentName() != null && fr.getStudentName().toLowerCase().contains(q)) list.add(fr);
            }
        }
        return list;
    }
    private static String getHeader(String title, String activeNav, String extraNav) {
        return "<!DOCTYPE html><html lang='en'><head><meta charset='UTF-8'><meta name='viewport' content='width=device-width, initial-scale=1.0'><title>" + title + "</title><link rel='stylesheet' href='/css/style.css'></head><body>" +
            "<nav class='navbar'><a href='/' class='logo'><div class='logo-icon'>ID</div>Smart ID Recovery</a><ul class='nav-links'>" +
            "<li><a href='/' " + ("Home".equals(activeNav) ? "class='active'" : "") + ">Home</a></li>" +
            "<li><a href='/search' " + ("Search Status".equals(activeNav) ? "class='active'" : "") + ">Search Status</a></li>" +
            "<li><a href='/reportFound' " + ("Report Found ID".equals(activeNav) ? "class='active'" : "") + ">Report Found ID</a></li>" + extraNav +
            "</ul></nav>";
    }
        return map;
    }
    private static Map<String, String> parseQueryParams(String query) {
        Map<String, String> map = new HashMap<>();
        if (query == null || query.isEmpty()) return map;
        String[] pairs = query.split("&");
        for (String pair : pairs) {
            String[] kv = pair.split("=");
            if (kv.length > 0) {
                String k = URLDecoder.decode(kv[0], StandardCharsets.UTF_8);
                String v = kv.length > 1 ? URLDecoder.decode(kv[1], StandardCharsets.UTF_8) : "";
                map.put(k, v);
            }
        }
        return map;
    }
    private static Map<String, String> parseCookies(String cookieHeader) {
        Map<String, String> map = new HashMap<>();
        if (cookieHeader == null) return map;
        try (OutputStream os = exchange.getResponseBody()) { os.write(bytes); }
    }
}