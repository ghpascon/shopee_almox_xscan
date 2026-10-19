package com.smartx.rfidreader.core.settings;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010 \n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b1\b\u0086\b\u0018\u00002\u00020\u0001B\u00b1\u0001\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007\u0012\u000e\b\u0002\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00030\t\u0012\u000e\b\u0002\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00070\t\u0012\b\b\u0002\u0010\u000b\u001a\u00020\u0003\u0012\b\b\u0002\u0010\f\u001a\u00020\u0003\u0012\b\b\u0002\u0010\r\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u000e\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u000f\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0010\u001a\u00020\u0011\u0012\b\b\u0002\u0010\u0012\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0013\u001a\u00020\u0007\u0012\b\b\u0002\u0010\u0014\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0015\u001a\u00020\u0007\u0012\b\b\u0002\u0010\u0016\u001a\u00020\u0007\u00a2\u0006\u0002\u0010\u0017J\t\u0010-\u001a\u00020\u0003H\u00c6\u0003J\t\u0010.\u001a\u00020\u0003H\u00c6\u0003J\t\u0010/\u001a\u00020\u0011H\u00c6\u0003J\t\u00100\u001a\u00020\u0003H\u00c6\u0003J\t\u00101\u001a\u00020\u0007H\u00c6\u0003J\t\u00102\u001a\u00020\u0003H\u00c6\u0003J\t\u00103\u001a\u00020\u0007H\u00c6\u0003J\t\u00104\u001a\u00020\u0007H\u00c6\u0003J\t\u00105\u001a\u00020\u0005H\u00c6\u0003J\t\u00106\u001a\u00020\u0007H\u00c6\u0003J\u000f\u00107\u001a\b\u0012\u0004\u0012\u00020\u00030\tH\u00c6\u0003J\u000f\u00108\u001a\b\u0012\u0004\u0012\u00020\u00070\tH\u00c6\u0003J\t\u00109\u001a\u00020\u0003H\u00c6\u0003J\t\u0010:\u001a\u00020\u0003H\u00c6\u0003J\t\u0010;\u001a\u00020\u0003H\u00c6\u0003J\t\u0010<\u001a\u00020\u0003H\u00c6\u0003J\u00b5\u0001\u0010=\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\u000e\b\u0002\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00030\t2\u000e\b\u0002\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00070\t2\b\b\u0002\u0010\u000b\u001a\u00020\u00032\b\b\u0002\u0010\f\u001a\u00020\u00032\b\b\u0002\u0010\r\u001a\u00020\u00032\b\b\u0002\u0010\u000e\u001a\u00020\u00032\b\b\u0002\u0010\u000f\u001a\u00020\u00032\b\b\u0002\u0010\u0010\u001a\u00020\u00112\b\b\u0002\u0010\u0012\u001a\u00020\u00032\b\b\u0002\u0010\u0013\u001a\u00020\u00072\b\b\u0002\u0010\u0014\u001a\u00020\u00032\b\b\u0002\u0010\u0015\u001a\u00020\u00072\b\b\u0002\u0010\u0016\u001a\u00020\u0007H\u00c6\u0001J\u0013\u0010>\u001a\u00020\u00052\b\u0010?\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010@\u001a\u00020\u0007H\u00d6\u0001J\t\u0010A\u001a\u00020\u0003H\u00d6\u0001R\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R\u0011\u0010\f\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u001bR\u0011\u0010\r\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001bR\u0017\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00070\t\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001eR\u0011\u0010\u000e\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010\u001bR\u0011\u0010\u000f\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b \u0010\u001bR\u0011\u0010\u0014\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b!\u0010\u001bR\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\"\u0010\u001bR\u0017\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00030\t\u00a2\u0006\b\n\u0000\u001a\u0004\b#\u0010\u001eR\u0011\u0010\u0016\u001a\u00020\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b$\u0010%R\u0011\u0010\u0015\u001a\u00020\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b&\u0010%R\u0011\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\'\u0010%R\u0011\u0010\u0010\u001a\u00020\u0011\u00a2\u0006\b\n\u0000\u001a\u0004\b(\u0010)R\u0011\u0010\u0013\u001a\u00020\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b*\u0010%R\u0011\u0010\u0012\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b+\u0010\u001bR\u0011\u0010\u000b\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b,\u0010\u001b\u00a8\u0006B"}, d2 = {"Lcom/smartx/rfidreader/core/settings/AppSettings;", "", "lastReaderId", "", "buzzerEnabled", "", "rssiFilter", "", "prefixes", "", "epcLengths", "xtrackUrl", "entryLocationId", "entryLocationName", "exitLocationId", "exitLocationName", "selectedOperation", "Lcom/smartx/rfidreader/core/settings/OperationType;", "webhookUrl", "webhookIntervalSeconds", "lastBleAddress", "readerTxPower", "readerSession", "(Ljava/lang/String;ZILjava/util/List;Ljava/util/List;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lcom/smartx/rfidreader/core/settings/OperationType;Ljava/lang/String;ILjava/lang/String;II)V", "getBuzzerEnabled", "()Z", "getEntryLocationId", "()Ljava/lang/String;", "getEntryLocationName", "getEpcLengths", "()Ljava/util/List;", "getExitLocationId", "getExitLocationName", "getLastBleAddress", "getLastReaderId", "getPrefixes", "getReaderSession", "()I", "getReaderTxPower", "getRssiFilter", "getSelectedOperation", "()Lcom/smartx/rfidreader/core/settings/OperationType;", "getWebhookIntervalSeconds", "getWebhookUrl", "getXtrackUrl", "component1", "component10", "component11", "component12", "component13", "component14", "component15", "component16", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "equals", "other", "hashCode", "toString", "app_debug"})
public final class AppSettings {
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String lastReaderId = null;
    private final boolean buzzerEnabled = false;
    private final int rssiFilter = 0;
    @org.jetbrains.annotations.NotNull()
    private final java.util.List<java.lang.String> prefixes = null;
    @org.jetbrains.annotations.NotNull()
    private final java.util.List<java.lang.Integer> epcLengths = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String xtrackUrl = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String entryLocationId = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String entryLocationName = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String exitLocationId = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String exitLocationName = null;
    @org.jetbrains.annotations.NotNull()
    private final com.smartx.rfidreader.core.settings.OperationType selectedOperation = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String webhookUrl = null;
    private final int webhookIntervalSeconds = 0;
    
    /**
     * Último endereço MAC BLE selecionado (global)
     */
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String lastBleAddress = null;
    
    /**
     * Potência TX do leitor persistida entre reinícios
     */
    private final int readerTxPower = 0;
    
    /**
     * Session do leitor persistida entre reinícios
     */
    private final int readerSession = 0;
    
    public AppSettings(@org.jetbrains.annotations.NotNull()
    java.lang.String lastReaderId, boolean buzzerEnabled, int rssiFilter, @org.jetbrains.annotations.NotNull()
    java.util.List<java.lang.String> prefixes, @org.jetbrains.annotations.NotNull()
    java.util.List<java.lang.Integer> epcLengths, @org.jetbrains.annotations.NotNull()
    java.lang.String xtrackUrl, @org.jetbrains.annotations.NotNull()
    java.lang.String entryLocationId, @org.jetbrains.annotations.NotNull()
    java.lang.String entryLocationName, @org.jetbrains.annotations.NotNull()
    java.lang.String exitLocationId, @org.jetbrains.annotations.NotNull()
    java.lang.String exitLocationName, @org.jetbrains.annotations.NotNull()
    com.smartx.rfidreader.core.settings.OperationType selectedOperation, @org.jetbrains.annotations.NotNull()
    java.lang.String webhookUrl, int webhookIntervalSeconds, @org.jetbrains.annotations.NotNull()
    java.lang.String lastBleAddress, int readerTxPower, int readerSession) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getLastReaderId() {
        return null;
    }
    
    public final boolean getBuzzerEnabled() {
        return false;
    }
    
    public final int getRssiFilter() {
        return 0;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<java.lang.String> getPrefixes() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<java.lang.Integer> getEpcLengths() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getXtrackUrl() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getEntryLocationId() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getEntryLocationName() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getExitLocationId() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getExitLocationName() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.smartx.rfidreader.core.settings.OperationType getSelectedOperation() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getWebhookUrl() {
        return null;
    }
    
    public final int getWebhookIntervalSeconds() {
        return 0;
    }
    
    /**
     * Último endereço MAC BLE selecionado (global)
     */
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getLastBleAddress() {
        return null;
    }
    
    /**
     * Potência TX do leitor persistida entre reinícios
     */
    public final int getReaderTxPower() {
        return 0;
    }
    
    /**
     * Session do leitor persistida entre reinícios
     */
    public final int getReaderSession() {
        return 0;
    }
    
    public AppSettings() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component1() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component10() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.smartx.rfidreader.core.settings.OperationType component11() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component12() {
        return null;
    }
    
    public final int component13() {
        return 0;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component14() {
        return null;
    }
    
    public final int component15() {
        return 0;
    }
    
    public final int component16() {
        return 0;
    }
    
    public final boolean component2() {
        return false;
    }
    
    public final int component3() {
        return 0;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<java.lang.String> component4() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<java.lang.Integer> component5() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component6() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component7() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component8() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component9() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.smartx.rfidreader.core.settings.AppSettings copy(@org.jetbrains.annotations.NotNull()
    java.lang.String lastReaderId, boolean buzzerEnabled, int rssiFilter, @org.jetbrains.annotations.NotNull()
    java.util.List<java.lang.String> prefixes, @org.jetbrains.annotations.NotNull()
    java.util.List<java.lang.Integer> epcLengths, @org.jetbrains.annotations.NotNull()
    java.lang.String xtrackUrl, @org.jetbrains.annotations.NotNull()
    java.lang.String entryLocationId, @org.jetbrains.annotations.NotNull()
    java.lang.String entryLocationName, @org.jetbrains.annotations.NotNull()
    java.lang.String exitLocationId, @org.jetbrains.annotations.NotNull()
    java.lang.String exitLocationName, @org.jetbrains.annotations.NotNull()
    com.smartx.rfidreader.core.settings.OperationType selectedOperation, @org.jetbrains.annotations.NotNull()
    java.lang.String webhookUrl, int webhookIntervalSeconds, @org.jetbrains.annotations.NotNull()
    java.lang.String lastBleAddress, int readerTxPower, int readerSession) {
        return null;
    }
    
    @java.lang.Override()
    public boolean equals(@org.jetbrains.annotations.Nullable()
    java.lang.Object other) {
        return false;
    }
    
    @java.lang.Override()
    public int hashCode() {
        return 0;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public java.lang.String toString() {
        return null;
    }
}