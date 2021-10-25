//package halo.android.adk;
//
//import android.os.Bundle;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.TextView;
//
//import androidx.annotation.NonNull;
//import androidx.annotation.Nullable;
//import androidx.fragment.app.Fragment;
//import halo.android.integration.qq.QQ;
//import halo.android.integration.qq.impl.QQLoginListener;
//import halo.android.integration.qq.model.QQApiResult;
//import halo.android.integration.qq.model.QQAuthInfo;
//
///**
// * Created by Lucio on 2021/10/25.
// */
//public class QQTestFragment extends Fragment {
//
//
//    /**
//     * Called to have the fragment instantiate its user interface view.
//     * This is optional, and non-graphical fragments can return null. This will be called between
//     * {@link #onCreate(Bundle)} and {@link #onActivityCreated(Bundle)}.
//     * <p>A default View can be returned by calling {@link #Fragment(int)} in your
//     * constructor. Otherwise, this method returns null.
//     *
//     * <p>It is recommended to <strong>only</strong> inflate the layout in this method and move
//     * logic that operates on the returned View to {@link #onViewCreated(View, Bundle)}.
//     *
//     * <p>If you return a View from here, you will later be called in
//     * {@link #onDestroyView} when the view is being released.
//     *
//     * @param inflater           The LayoutInflater object that can be used to inflate
//     *                           any views in the fragment,
//     * @param container          If non-null, this is the parent view that the fragment's
//     *                           UI should be attached to.  The fragment should not add the view itself,
//     *                           but this can be used to generate the LayoutParams of the view.
//     * @param savedInstanceState If non-null, this fragment is being re-constructed
//     *                           from a previous saved state as given here.
//     * @return Return the View for the fragment's UI, or null.
//     */
//    @Nullable
//    @Override
//    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        return new TextView(requireContext());
//    }
//
//    /**
//     * Called immediately after {@link #onCreateView(LayoutInflater, ViewGroup, Bundle)}
//     * has returned, but before any saved state has been restored in to the view.
//     * This gives subclasses a chance to initialize themselves once
//     * they know their view hierarchy has been completely created.  The fragment's
//     * view hierarchy is not however attached to its parent at this point.
//     *
//     * @param view               The View returned by {@link #onCreateView(LayoutInflater, ViewGroup, Bundle)}.
//     * @param savedInstanceState If non-null, this fragment is being re-constructed
//     */
//    @Override
//    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
//        super.onViewCreated(view, savedInstanceState);
//        QQ.buildQQAction(this,"1111135356").login(new QQLoginListener() {
//            @Override
//            public void onQQLoginSuccess(QQAuthInfo authInfo) {
//
//            }
//
//            @Override
//            public void onQQApiFailed(QQApiResult error) {
//
//            }
//
//            @Override
//            public void onQQApiCancel() {
//
//            }
//        });
//    }
//}
