package com.smartx.rfidreader.core.xtrack;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0094\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\t\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0012\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0010\u0010\u0019\u001a\u00020\u00042\u0006\u0010\u001a\u001a\u00020\u0004H\u0002J\u0016\u0010\u001b\u001a\u00020\u00042\f\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u00040\u001dH\u0002J\u0010\u0010\u001e\u001a\u00020\u00042\u0006\u0010\u001f\u001a\u00020\u0004H\u0002J\u001e\u0010 \u001a\u00020\u00042\f\u0010!\u001a\b\u0012\u0004\u0012\u00020\u00040\u001d2\u0006\u0010\"\u001a\u00020\u0004H\u0002J\u001e\u0010#\u001a\u00020\u00042\f\u0010!\u001a\b\u0012\u0004\u0012\u00020\u00040\u001d2\u0006\u0010$\u001a\u00020\u0004H\u0002J$\u0010%\u001a\b\u0012\u0004\u0012\u00020\'0&2\u0006\u0010(\u001a\u00020\u0004H\u0086@\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\u0004\b)\u0010*J\u0018\u0010+\u001a\u00020\u00042\u0006\u0010,\u001a\u00020\u00042\u0006\u0010-\u001a\u00020\u0004H\u0002J*\u0010.\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020/0\u001d0&2\u0006\u0010(\u001a\u00020\u0004H\u0086@\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\u0004\b0\u0010*J,\u00101\u001a\b\u0012\u0004\u0012\u0002020&2\u0006\u0010(\u001a\u00020\u00042\u0006\u0010\u001a\u001a\u00020\u0004H\u0086@\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\u0004\b3\u00104J*\u00105\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002020\u001d0&2\u0006\u0010(\u001a\u00020\u0004H\u0086@\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\u0004\b6\u0010*J*\u00107\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002080\u001d0&2\u0006\u0010(\u001a\u00020\u0004H\u0086@\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\u0004\b9\u0010*J>\u0010:\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020<0;0&2\u0006\u0010(\u001a\u00020\u00042\f\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u00040\u001dH\u0086@\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\u0004\b=\u0010>J&\u0010\u001f\u001a\u00020?2\u0006\u0010(\u001a\u00020\u00042\u0006\u0010@\u001a\u00020\u00042\u0006\u0010A\u001a\u00020\u0004H\u0086@\u00a2\u0006\u0002\u0010BJ\u0006\u0010C\u001a\u00020\'J\u0010\u0010D\u001a\u00020\u00042\u0006\u0010E\u001a\u00020\u0004H\u0002J:\u0010F\u001a\b\u0012\u0004\u0012\u00020\u000e0&2\u0006\u0010(\u001a\u00020\u00042\f\u0010!\u001a\b\u0012\u0004\u0012\u00020\u00040\u001d2\u0006\u0010\"\u001a\u00020\u0004H\u0086@\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\u0004\bG\u0010HJ:\u0010I\u001a\b\u0012\u0004\u0012\u00020\u000e0&2\u0006\u0010(\u001a\u00020\u00042\f\u0010!\u001a\b\u0012\u0004\u0012\u00020\u00040\u001d2\u0006\u0010$\u001a\u00020\u0004H\u0086@\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\u0004\bJ\u0010HJ\u001b\u0010K\u001a\b\u0012\u0004\u0012\u00020/0\u001d2\u0006\u0010L\u001a\u00020\u0004H\u0000\u00a2\u0006\u0002\bMJ\u0016\u0010N\u001a\b\u0012\u0004\u0012\u00020O0\u001d2\u0006\u0010L\u001a\u00020\u0004H\u0002J\u0012\u0010P\u001a\u0004\u0018\u0001022\u0006\u0010Q\u001a\u00020RH\u0002J\u0016\u0010S\u001a\b\u0012\u0004\u0012\u0002020\u001d2\u0006\u0010T\u001a\u00020\u0004H\u0002J\u0016\u0010U\u001a\b\u0012\u0004\u0012\u0002020\u001d2\u0006\u0010V\u001a\u00020WH\u0002J\u001b\u0010X\u001a\b\u0012\u0004\u0012\u0002020\u001d2\u0006\u0010Y\u001a\u00020\u0004H\u0000\u00a2\u0006\u0002\bZJ\u001b\u0010[\u001a\b\u0012\u0004\u0012\u0002020\u001d2\u0006\u0010L\u001a\u00020\u0004H\u0000\u00a2\u0006\u0002\b\\J\u001b\u0010]\u001a\b\u0012\u0004\u0012\u0002080\u001d2\u0006\u0010L\u001a\u00020\u0004H\u0000\u00a2\u0006\u0002\b^J!\u0010_\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020<0;2\u0006\u0010L\u001a\u00020\u0004H\u0000\u00a2\u0006\u0002\b`J\u0018\u0010a\u001a\u00020\u00042\u0006\u0010b\u001a\u00020\u00042\u0006\u0010c\u001a\u00020\u0004H\u0002J\u0016\u0010d\u001a\b\u0012\u0004\u0012\u0002080\u001d2\u0006\u0010b\u001a\u00020\u0004H\u0002J\u0010\u0010e\u001a\u00020\u00042\u0006\u0010E\u001a\u00020\u0004H\u0002J\u0010\u0010f\u001a\u00020\u00042\u0006\u0010E\u001a\u00020\u0004H\u0002J\u0016\u0010g\u001a\u00020\u0004*\u00020\u00042\b\b\u0002\u0010h\u001a\u00020\u000eH\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u0016\u0010\u0010\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00120\u0011X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0019\u0010\u0013\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00120\u0014\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u000e\u0010\u0017\u001a\u00020\u0018X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u0082\u0002\u000b\n\u0002\b!\n\u0005\b\u00a1\u001e0\u0001\u00a8\u0006i"}, d2 = {"Lcom/smartx/rfidreader/core/xtrack/xtrack_manager;", "", "()V", "GET_CONDITION_PAYLOAD", "", "GET_DEVICE_PAYLOAD", "GET_LOCATION_PAYLOAD", "GET_OBJECT_COMMAND", "MOVE_CONDITION_COMMAND", "MOVE_LOCATION_COMMAND", "MOVE_LOCATION_TERMINAL", "NETWORK_TIMEOUT_SECONDS", "", "RESPONSE_PREVIEW_MAX", "", "TAG", "_currentUser", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Lcom/smartx/rfidreader/core/xtrack/XTrackUser;", "currentUser", "Lkotlinx/coroutines/flow/StateFlow;", "getCurrentUser", "()Lkotlinx/coroutines/flow/StateFlow;", "httpClient", "Lokhttp3/OkHttpClient;", "buildGetDeviceByNamePayload", "deviceName", "buildGetObjectPayload", "epcs", "", "buildGetUserPayload", "login", "buildMoveConditionPayload", "objectIdCodes", "conditionName", "buildMoveLocationPayload", "locationName", "checkCommunication", "Lkotlin/Result;", "", "xtrackUrl", "checkCommunication-gIAlu-s", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "extractFirstValue", "xmlBlock", "tagName", "fetchConditions", "Lcom/smartx/rfidreader/core/xtrack/XTrackCondition;", "fetchConditions-gIAlu-s", "fetchDeviceByName", "Lcom/smartx/rfidreader/core/xtrack/XTrackDevice;", "fetchDeviceByName-0E7RQCE", "(Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "fetchDevices", "fetchDevices-gIAlu-s", "fetchLocations", "Lcom/smartx/rfidreader/core/xtrack/XTrackLocation;", "fetchLocations-gIAlu-s", "fetchObjectInfos", "", "Lcom/smartx/rfidreader/core/xtrack/XTrackObjectInfo;", "fetchObjectInfos-0E7RQCE", "(Ljava/lang/String;Ljava/util/List;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Lcom/smartx/rfidreader/core/xtrack/XTrackLoginResult;", "username", "password", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "logout", "md5Upper", "value", "moveCondition", "moveCondition-BWLJW6A", "(Ljava/lang/String;Ljava/util/List;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "moveLocation", "moveLocation-BWLJW6A", "parseConditionsXml", "xml", "parseConditionsXml$app_debug", "parseDataRows", "Lcom/smartx/rfidreader/core/xtrack/ParsedRow;", "parseDeviceJsonObject", "obj", "Lorg/json/JSONObject;", "parseDevicesJson", "jsonRaw", "parseDevicesJsonArray", "array", "Lorg/json/JSONArray;", "parseDevicesResponse", "rawBody", "parseDevicesResponse$app_debug", "parseDevicesXml", "parseDevicesXml$app_debug", "parseLocationsXml", "parseLocationsXml$app_debug", "parseObjectInfosXml", "parseObjectInfosXml$app_debug", "postXml", "url", "payload", "requestLocations", "xmlEscape", "xmlUnescape", "preview", "max", "app_debug"})
public final class xtrack_manager {
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String TAG = "xtrack_manager";
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String GET_LOCATION_PAYLOAD = "<msg><command>GetLocation</command><terminal>ERP</terminal></msg>";
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String GET_DEVICE_PAYLOAD = "<msg><command>GetDevice</command><terminal>ERP</terminal></msg>";
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String GET_CONDITION_PAYLOAD = "<msg><command>GetCondition</command><terminal>SAPext</terminal></msg>";
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String GET_OBJECT_COMMAND = "GetObject";
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String MOVE_LOCATION_COMMAND = "MoveLocation";
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String MOVE_CONDITION_COMMAND = "MoveCondition";
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String MOVE_LOCATION_TERMINAL = "SAPext";
    private static final int RESPONSE_PREVIEW_MAX = 500;
    private static final long NETWORK_TIMEOUT_SECONDS = 5L;
    @org.jetbrains.annotations.NotNull()
    private static final okhttp3.OkHttpClient httpClient = null;
    @org.jetbrains.annotations.NotNull()
    private static final kotlinx.coroutines.flow.MutableStateFlow<com.smartx.rfidreader.core.xtrack.XTrackUser> _currentUser = null;
    @org.jetbrains.annotations.NotNull()
    private static final kotlinx.coroutines.flow.StateFlow<com.smartx.rfidreader.core.xtrack.XTrackUser> currentUser = null;
    @org.jetbrains.annotations.NotNull()
    public static final com.smartx.rfidreader.core.xtrack.xtrack_manager INSTANCE = null;
    
    private xtrack_manager() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<com.smartx.rfidreader.core.xtrack.XTrackUser> getCurrentUser() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object login(@org.jetbrains.annotations.NotNull()
    java.lang.String xtrackUrl, @org.jetbrains.annotations.NotNull()
    java.lang.String username, @org.jetbrains.annotations.NotNull()
    java.lang.String password, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.smartx.rfidreader.core.xtrack.XTrackLoginResult> $completion) {
        return null;
    }
    
    public final void logout() {
    }
    
    private final java.util.List<com.smartx.rfidreader.core.xtrack.XTrackLocation> requestLocations(java.lang.String url) {
        return null;
    }
    
    private final java.lang.String postXml(java.lang.String url, java.lang.String payload) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.smartx.rfidreader.core.xtrack.XTrackLocation> parseLocationsXml$app_debug(@org.jetbrains.annotations.NotNull()
    java.lang.String xml) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.Map<java.lang.String, com.smartx.rfidreader.core.xtrack.XTrackObjectInfo> parseObjectInfosXml$app_debug(@org.jetbrains.annotations.NotNull()
    java.lang.String xml) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.smartx.rfidreader.core.xtrack.XTrackCondition> parseConditionsXml$app_debug(@org.jetbrains.annotations.NotNull()
    java.lang.String xml) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.smartx.rfidreader.core.xtrack.XTrackDevice> parseDevicesXml$app_debug(@org.jetbrains.annotations.NotNull()
    java.lang.String xml) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.smartx.rfidreader.core.xtrack.XTrackDevice> parseDevicesResponse$app_debug(@org.jetbrains.annotations.NotNull()
    java.lang.String rawBody) {
        return null;
    }
    
    private final java.util.List<com.smartx.rfidreader.core.xtrack.XTrackDevice> parseDevicesJson(java.lang.String jsonRaw) {
        return null;
    }
    
    private final java.util.List<com.smartx.rfidreader.core.xtrack.XTrackDevice> parseDevicesJsonArray(org.json.JSONArray array) {
        return null;
    }
    
    private final com.smartx.rfidreader.core.xtrack.XTrackDevice parseDeviceJsonObject(org.json.JSONObject obj) {
        return null;
    }
    
    private final java.util.List<com.smartx.rfidreader.core.xtrack.ParsedRow> parseDataRows(java.lang.String xml) {
        return null;
    }
    
    private final java.lang.String xmlUnescape(java.lang.String value) {
        return null;
    }
    
    private final java.lang.String md5Upper(java.lang.String value) {
        return null;
    }
    
    private final java.lang.String buildGetObjectPayload(java.util.List<java.lang.String> epcs) {
        return null;
    }
    
    private final java.lang.String buildGetUserPayload(java.lang.String login) {
        return null;
    }
    
    private final java.lang.String buildGetDeviceByNamePayload(java.lang.String deviceName) {
        return null;
    }
    
    private final java.lang.String buildMoveLocationPayload(java.util.List<java.lang.String> objectIdCodes, java.lang.String locationName) {
        return null;
    }
    
    private final java.lang.String buildMoveConditionPayload(java.util.List<java.lang.String> objectIdCodes, java.lang.String conditionName) {
        return null;
    }
    
    private final java.lang.String extractFirstValue(java.lang.String xmlBlock, java.lang.String tagName) {
        return null;
    }
    
    private final java.lang.String xmlEscape(java.lang.String value) {
        return null;
    }
    
    private final java.lang.String preview(java.lang.String $this$preview, int max) {
        return null;
    }
}