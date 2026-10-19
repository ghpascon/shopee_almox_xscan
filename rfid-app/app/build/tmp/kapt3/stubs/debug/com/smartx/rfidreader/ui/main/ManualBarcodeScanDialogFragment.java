package com.smartx.rfidreader.ui.main;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0010\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0013H\u0002J\u0012\u0010\u0014\u001a\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u0017H\u0016R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001b\u0010\b\u001a\u00020\t8BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\f\u0010\r\u001a\u0004\b\n\u0010\u000bR\u000e\u0010\u000e\u001a\u00020\u000fX\u0082.\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0018"}, d2 = {"Lcom/smartx/rfidreader/ui/main/ManualBarcodeScanDialogFragment;", "Landroidx/fragment/app/DialogFragment;", "()V", "closingAfterSuccess", "", "editCapture", "Landroid/widget/EditText;", "internalEditUpdate", "mainViewModel", "Lcom/smartx/rfidreader/ui/main/MainViewModel;", "getMainViewModel", "()Lcom/smartx/rfidreader/ui/main/MainViewModel;", "mainViewModel$delegate", "Lkotlin/Lazy;", "textLastValue", "Landroid/widget/TextView;", "consumeBarcode", "", "rawValue", "", "onCreateDialog", "Landroidx/appcompat/app/AlertDialog;", "savedInstanceState", "Landroid/os/Bundle;", "app_debug"})
public final class ManualBarcodeScanDialogFragment extends androidx.fragment.app.DialogFragment {
    @org.jetbrains.annotations.NotNull()
    private final kotlin.Lazy mainViewModel$delegate = null;
    private android.widget.TextView textLastValue;
    private android.widget.EditText editCapture;
    private boolean internalEditUpdate = false;
    private boolean closingAfterSuccess = false;
    
    public ManualBarcodeScanDialogFragment() {
        super();
    }
    
    private final com.smartx.rfidreader.ui.main.MainViewModel getMainViewModel() {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public androidx.appcompat.app.AlertDialog onCreateDialog(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
        return null;
    }
    
    private final void consumeBarcode(java.lang.String rawValue) {
    }
}