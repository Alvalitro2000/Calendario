package codewithcal.au.calendarappexample;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements CalendarAdapter.OnItemListener
{
    private TextView monthYearText;
    private RecyclerView calendarRecyclerView;
    private LocalDate selectedDate;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initWidgets();
        selectedDate = LocalDate.now();
        setMonthView();
    }

    private void initWidgets()
    {
        calendarRecyclerView = findViewById(R.id.calendarRecyclerView);
        monthYearText = findViewById(R.id.monthYearTV);
    }

    private void setMonthView()
    {
        monthYearText.setText(monthYearFromDate(selectedDate));
        ArrayList<String> daysInMonth = daysInMonthArray(selectedDate);
        if (selectedDate.getMonthValue()==1){
            monthYearText.setText("Enero "+selectedDate.getYear());
        }
        else if (selectedDate.getMonthValue()==2){
            monthYearText.setText("Febrero "+selectedDate.getYear());
        }
        else if (selectedDate.getMonthValue()==3){
            monthYearText.setText("Marzo "+selectedDate.getYear());
        }
        else if (selectedDate.getMonthValue()==4){
            monthYearText.setText("Abril "+selectedDate.getYear());
        }
        else if (selectedDate.getMonthValue()==5){
            monthYearText.setText("Mayo "+selectedDate.getYear());
        }
        else if (selectedDate.getMonthValue()==6){
            monthYearText.setText("Junio "+selectedDate.getYear());
        }
        else if (selectedDate.getMonthValue()==7) {
            monthYearText.setText("Julio " + selectedDate.getYear());
        }
        else if (selectedDate.getMonthValue()==8){
            monthYearText.setText("Agosto "+selectedDate.getYear());
        }
        else if (selectedDate.getMonthValue()==9){
            monthYearText.setText("Septiembre "+selectedDate.getYear());
        }
        else if (selectedDate.getMonthValue()==10) {
            monthYearText.setText("Octubre " + selectedDate.getYear());
        }
        else if (selectedDate.getMonthValue()==11) {
            monthYearText.setText("Noviembre " + selectedDate.getYear());
        }
        else if (selectedDate.getMonthValue()==12) {
            monthYearText.setText("Diciembre " + selectedDate.getYear());
        }
        CalendarAdapter calendarAdapter = new CalendarAdapter(daysInMonth, this);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getApplicationContext(), 7);
        calendarRecyclerView.setLayoutManager(layoutManager);
        calendarRecyclerView.setAdapter(calendarAdapter);
    }

    private ArrayList<String> daysInMonthArray(LocalDate date)
    {
        ArrayList<String> daysInMonthArray = new ArrayList<>();
        YearMonth yearMonth = YearMonth.from(date);

        int daysInMonth = yearMonth.lengthOfMonth();

        LocalDate firstOfMonth = selectedDate.withDayOfMonth(1);
        int dayOfWeek = firstOfMonth.getDayOfWeek().getValue();

        for(int i = 1; i <= 42; i++)
        {
            if(i <= dayOfWeek || i > daysInMonth + dayOfWeek)
            {
                daysInMonthArray.add("");
            }
            else
            {
                daysInMonthArray.add(String.valueOf(i - dayOfWeek));
            }
        }
        return  daysInMonthArray;
    }

    private String monthYearFromDate(LocalDate date)
    {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM");
        return date.format(formatter);
    }

    public void previousMonthAction(View view)
    {
        selectedDate = selectedDate.minusMonths(1);
        setMonthView();
    }

    public void nextMonthAction(View view)
    {
        selectedDate = selectedDate.plusMonths(1);
        setMonthView();
    }

    @Override
    public void onItemClick(int position, String dayText)
    {
        if(!dayText.equals(""))
        {
            String message = "Dia seleccionado " + dayText + " " + monthYearFromDate(selectedDate);
            Toast.makeText(this, message, Toast.LENGTH_LONG).show();
        }
    }
}








