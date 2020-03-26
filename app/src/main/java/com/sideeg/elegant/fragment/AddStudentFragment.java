package com.sideeg.elegant.fragment;

import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import com.sideeg.elegant.NetWorkApis.ApiClient;
import com.sideeg.elegant.NetWorkApis.NetWorkApis;
import com.sideeg.elegant.R;
import com.sideeg.elegant.model.BaseRespnse;
import com.sideeg.elegant.model.GetParentAndSupervisorRespnse;
import com.sideeg.elegant.model.ParentData;
import com.sideeg.elegant.model.SuperVisorData;
import com.sideeg.elegant.utiltiy.SessionManger;
import com.sideeg.elegant.utiltiy.Utility;
import java.util.ArrayList;
import java.util.List;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddStudentFragment extends Fragment implements AdapterView.OnItemSelectedListener {

    private static final String TAG = "AddStudentFragment";
    private Spinner parentSpinner, superVisorSpinner;
    private Button btnSubmit;
    private EditText studentName,className;
    private View root;
    private List<Integer> parentListInt ;
    private List<Integer> superVisorListInt ;
    private  int selectedParent ;
    private  int selectedSuperVisore ;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
         root = inflater.inflate(R.layout.fragment_add_student, container, false);
        btnSubmit = root.findViewById(R.id.create_student);
        studentName = root.findViewById(R.id.add_syudent_Name);
         className = root.findViewById(R.id.add_student_class);

        parentSpinner = root.findViewById(R.id.spinner_parent);
        superVisorSpinner = root.findViewById(R.id.spinner_supervisor);



        addItemsOnSpinner();
        addItemsOnsuperVisorSpinner();
        addListenerOnButton();
        addListenerOnSpinnerItemSelection();


        return root;
    }

    private void addListenerOnSpinnerItemSelection() {
        parentSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                selectedParent = parentListInt.get(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        superVisorSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                selectedSuperVisore = superVisorListInt.get(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void addListenerOnButton() {
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                NetWorkApis api = ApiClient.getClient(ApiClient.BASE_URL).create(NetWorkApis.class);
                String temp =new SessionManger(getContext()).getSchoolId();
                Call<BaseRespnse> loginCall = api.CreateStudent(studentName.getText().toString(),className.getText().toString(),temp,selectedSuperVisore,selectedParent);
                loginCall.enqueue(new Callback<BaseRespnse>() {
                    @Override
                    public void onResponse(Call<BaseRespnse> call, Response<BaseRespnse> response) {
                        if (response.isSuccessful()) {
                            if (response.body().isError()) {
                                Utility.showAlertDialog(getContext().getString(R.string.error), response.body().getMessage(), getContext());

                            } else {
                                final Dialog dialog = new Dialog(getContext());
                                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                                dialog.setContentView(R.layout.ok_dialog);
                                dialog.setCancelable(true);
                                dialog.setCanceledOnTouchOutside(false);

                                dialog.findViewById(R.id.dialogOkBtn).setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        dialog.dismiss();
                                        FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                                        ft.replace(R.id.container, new AllStudentFragment());
                                        ft.commit();

                                    }
                                });
                                dialog.show();

                            }
                        } else {
                            Log.i(TAG, response.errorBody().toString());
                            Utility.showAlertDialog(getContext().getString(R.string.error), getContext().getString(R.string.servererror), getContext());

                        }
                    }

                    @Override
                    public void onFailure(Call<BaseRespnse> call, Throwable t) {
                        Utility.showAlertDialog(getContext().getString(R.string.error), t.getMessage(), getContext());
                        Utility.printLog(TAG, t.getMessage());
                    }
                });

            }
        });
    }

    private void addItemsOnsuperVisorSpinner() {

    }

    private void addItemsOnSpinner() {

        NetWorkApis api = ApiClient.getClient(ApiClient.BASE_URL).create(NetWorkApis.class);
        String temp =new SessionManger(getContext()).getSchoolId();
        Call<GetParentAndSupervisorRespnse> loginCall = api.getparentandsupervisor(temp);
        loginCall.enqueue(new Callback<GetParentAndSupervisorRespnse>() {
            @Override
            public void onResponse(Call<GetParentAndSupervisorRespnse> call, Response<GetParentAndSupervisorRespnse> response) {
                if (response.isSuccessful()) {
                    if (response.body().isError()) {
                        Utility.showAlertDialog(getContext().getString(R.string.error), response.body().getMessage(), getContext());

                    } else {


                        parentListInt = new ArrayList<>();
                        superVisorListInt = new ArrayList<>();
                        List<String> parentListName = new ArrayList<String>();
                        for (ParentData parentData: response.body().getParent()) {
                            parentListName.add(parentData.getName());
                            parentListInt.add(parentData.getId());
                        }


                        List<String> supervisorListName = new ArrayList<String>();
                        for (SuperVisorData SuperVisorData: response.body().getSupervisor()) {
                            supervisorListName.add(SuperVisorData.getSupervisorName());
                            superVisorListInt.add(SuperVisorData.getSupervisor_id());
                        }



                        ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_spinner_item, supervisorListName);
                        dataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        superVisorSpinner.setAdapter(dataAdapter2);

                        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_spinner_item, parentListName);
                        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        parentSpinner.setAdapter(dataAdapter);

                    }
                } else {
                    Log.i(TAG, response.errorBody().toString());
                    Utility.showAlertDialog(getContext().getString(R.string.error), getContext().getString(R.string.servererror), getContext());

                }
            }

            @Override
            public void onFailure(Call<GetParentAndSupervisorRespnse> call, Throwable t) {
                Utility.showAlertDialog(getContext().getString(R.string.error), t.getMessage(), getContext());
                Utility.printLog(TAG, t.getMessage());
            }
        });



    }

    public void onItemSelected(AdapterView<?> parent, View view,
                               int pos, long id) {
        // An item was selected. You can retrieve the selected item using
        // parent.getItemAtPosition(pos)
    }

    public void onNothingSelected(AdapterView<?> parent) {
        // Another interface callback
    }
}
