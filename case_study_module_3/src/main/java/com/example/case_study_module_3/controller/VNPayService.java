package com.example.case_study_module_3.controller;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class VNPayService {
    private static final String VNP_TMNCODE = "PCLJDQ21";
    private static final String VNP_HASHSECRET = "U6VCMLUN8NG7YCSXFL74L7OWEJM60PVV";
        private static final String VNP_URL = "https://sandbox.vnpayment.vn/paymentv2/vpcpay.html";

    public static String generatePaymentUrl(String returnUrl, double amount, String orderInfo, String orderId) {
        Map<String, String> params = new HashMap<>();
        params.put("vnp_Version", "2.1.0");
        params.put("vnp_TmnCode", VNP_TMNCODE);
        params.put("vnp_Amount", String.valueOf((int) (amount * 100)));
        params.put("vnp_Command", "pay");
        params.put("vnp_CreateDate", getCurrentDate());
        params.put("vnp_CurrCode", "VND");
        params.put("vnp_IpAddr", "127.0.0.1");
        params.put("vnp_Locale", "vn");
        params.put("vnp_OrderInfo", orderInfo);
        params.put("vnp_OrderType", "billpayment");
        params.put("vnp_ReturnUrl", returnUrl);
        params.put("vnp_TxnRef", orderId);

        // Sắp xếp tham số theo thứ tự A-Z
        List<String> fieldNames = new ArrayList<>(params.keySet());
        Collections.sort(fieldNames);

        StringBuilder query = new StringBuilder();
        for (String fieldName : fieldNames) {
            String value = params.get(fieldName);
            if (value != null && !value.isEmpty()) {
                try {
                    query.append(URLEncoder.encode(fieldName, StandardCharsets.UTF_8.toString()))
                            .append("=")
                            .append(URLEncoder.encode(value, StandardCharsets.UTF_8.toString()))
                            .append("&");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        String queryUrl = query.substring(0, query.length() - 1);
        String secureHash = hmacSHA512(queryUrl, VNP_HASHSECRET);
        return VNP_URL + "?" + queryUrl + "&vnp_SecureHash=" + secureHash;
    }

    private static String getCurrentDate() {
        Calendar calendar = Calendar.getInstance();
        return String.format("%1$tY%1$tm%1$td%1$tH%1$tM%1$tS", calendar);
    }

    public static String hmacSHA512(String data, String secretKey) {
        try {
            Mac hmacSHA512 = Mac.getInstance("HmacSHA512");
            SecretKeySpec secretKeySpec = new SecretKeySpec(secretKey.getBytes(StandardCharsets.UTF_8), "HmacSHA512");
            hmacSHA512.init(secretKeySpec);
            byte[] bytes = hmacSHA512.doFinal(data.getBytes(StandardCharsets.UTF_8));
            StringBuilder hash = new StringBuilder();
            for (byte b : bytes) {
                hash.append(String.format("%02x", b));
            }
            return hash.toString();
        } catch (Exception e) {
            throw new RuntimeException("Không thể tạo HMAC-SHA512", e);
        }
    }
}
