package com.smartx.rfidreader.ui.main;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\bv\u0018\u00002\u00020\u0001:\u0002\u0002\u0003\u0082\u0001\u0002\u0004\u0005\u00a8\u0006\u0006"}, d2 = {"Lcom/smartx/rfidreader/ui/main/MoveLocationUiResult;", "", "Error", "Success", "Lcom/smartx/rfidreader/ui/main/MoveLocationUiResult$Error;", "Lcom/smartx/rfidreader/ui/main/MoveLocationUiResult$Success;", "app_debug"})
public abstract interface MoveLocationUiResult {
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\n\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\t\u0010\u000b\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\f\u001a\u00020\u0005H\u00c6\u0003J\u001d\u0010\r\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005H\u00c6\u0001J\u0013\u0010\u000e\u001a\u00020\u00052\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010H\u00d6\u0003J\t\u0010\u0011\u001a\u00020\u0012H\u00d6\u0001J\t\u0010\u0013\u001a\u00020\u0003H\u00d6\u0001R\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n\u00a8\u0006\u0014"}, d2 = {"Lcom/smartx/rfidreader/ui/main/MoveLocationUiResult$Error;", "Lcom/smartx/rfidreader/ui/main/MoveLocationUiResult;", "message", "", "canRetry", "", "(Ljava/lang/String;Z)V", "getCanRetry", "()Z", "getMessage", "()Ljava/lang/String;", "component1", "component2", "copy", "equals", "other", "", "hashCode", "", "toString", "app_debug"})
    public static final class Error implements com.smartx.rfidreader.ui.main.MoveLocationUiResult {
        @org.jetbrains.annotations.NotNull()
        private final java.lang.String message = null;
        private final boolean canRetry = false;
        
        public Error(@org.jetbrains.annotations.NotNull()
        java.lang.String message, boolean canRetry) {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String getMessage() {
            return null;
        }
        
        public final boolean getCanRetry() {
            return false;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String component1() {
            return null;
        }
        
        public final boolean component2() {
            return false;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.smartx.rfidreader.ui.main.MoveLocationUiResult.Error copy(@org.jetbrains.annotations.NotNull()
        java.lang.String message, boolean canRetry) {
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
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\b\u0086\b\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\u0002\u0010\bJ\t\u0010\u000f\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0010\u001a\u00020\u0005H\u00c6\u0003J\t\u0010\u0011\u001a\u00020\u0007H\u00c6\u0003J\'\u0010\u0012\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0007H\u00c6\u0001J\u0013\u0010\u0013\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u0016H\u00d6\u0003J\t\u0010\u0017\u001a\u00020\u0003H\u00d6\u0001J\t\u0010\u0018\u001a\u00020\u0005H\u00d6\u0001R\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000e\u00a8\u0006\u0019"}, d2 = {"Lcom/smartx/rfidreader/ui/main/MoveLocationUiResult$Success;", "Lcom/smartx/rfidreader/ui/main/MoveLocationUiResult;", "sentCount", "", "locationName", "", "operationType", "Lcom/smartx/rfidreader/core/settings/OperationType;", "(ILjava/lang/String;Lcom/smartx/rfidreader/core/settings/OperationType;)V", "getLocationName", "()Ljava/lang/String;", "getOperationType", "()Lcom/smartx/rfidreader/core/settings/OperationType;", "getSentCount", "()I", "component1", "component2", "component3", "copy", "equals", "", "other", "", "hashCode", "toString", "app_debug"})
    public static final class Success implements com.smartx.rfidreader.ui.main.MoveLocationUiResult {
        private final int sentCount = 0;
        @org.jetbrains.annotations.NotNull()
        private final java.lang.String locationName = null;
        @org.jetbrains.annotations.NotNull()
        private final com.smartx.rfidreader.core.settings.OperationType operationType = null;
        
        public Success(int sentCount, @org.jetbrains.annotations.NotNull()
        java.lang.String locationName, @org.jetbrains.annotations.NotNull()
        com.smartx.rfidreader.core.settings.OperationType operationType) {
            super();
        }
        
        public final int getSentCount() {
            return 0;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String getLocationName() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.smartx.rfidreader.core.settings.OperationType getOperationType() {
            return null;
        }
        
        public final int component1() {
            return 0;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String component2() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.smartx.rfidreader.core.settings.OperationType component3() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.smartx.rfidreader.ui.main.MoveLocationUiResult.Success copy(int sentCount, @org.jetbrains.annotations.NotNull()
        java.lang.String locationName, @org.jetbrains.annotations.NotNull()
        com.smartx.rfidreader.core.settings.OperationType operationType) {
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
}