package com.kesteli.filip.lauzhack;

import android.graphics.Color;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.Dash;
import com.google.android.gms.maps.model.Dot;
import com.google.android.gms.maps.model.Gap;
import com.google.android.gms.maps.model.JointType;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PatternItem;
import com.google.android.gms.maps.model.Polygon;
import com.google.android.gms.maps.model.PolygonOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.android.gms.maps.model.RoundCap;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.kesteli.filip.lauzhack.data.Texts;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback,
        GoogleMap.OnPolylineClickListener,
        GoogleMap.OnPolygonClickListener {

    private DatabaseReference databaseReference;
    private DatabaseReference childTexts;
    private DatabaseReference childText;

    private GoogleMap mMap;

    private static final int COLOR_BLACK_ARGB = 0xff000000;
    private static final int COLOR_WHITE_ARGB = 0xffffffff;
    private static final int COLOR_GREEN_ARGB = 0xff388E3C;
    private static final int COLOR_PURPLE_ARGB = 0xff81C784;
    private static final int COLOR_ORANGE_ARGB = 0xffF57F17;
    private static final int COLOR_BLUE_ARGB = 0xffF9A825;

    private static final int POLYLINE_STROKE_WIDTH_PX = 12;
    private static final int POLYGON_STROKE_WIDTH_PX = 8;
    private static final int PATTERN_DASH_LENGTH_PX = 20;
    private static final int PATTERN_GAP_LENGTH_PX = 20;
    private static final PatternItem DOT = new Dot();
    private static final PatternItem DASH = new Dash(PATTERN_DASH_LENGTH_PX);
    private static final PatternItem GAP = new Gap(PATTERN_GAP_LENGTH_PX);

    // Create a stroke pattern of a gap followed by a dot.
    private static final List<PatternItem> PATTERN_POLYLINE_DOTTED = Arrays.asList(GAP, DOT);

    // Create a stroke pattern of a gap followed by a dash.
    private static final List<PatternItem> PATTERN_POLYGON_ALPHA = Arrays.asList(GAP, DASH);

    // Create a stroke pattern of a dot followed by a gap, a dash, and another gap.
    private static final List<PatternItem> PATTERN_POLYGON_BETA =
            Arrays.asList(DOT, GAP, DASH, GAP);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Retrieve the content view that renders the map.
        setContentView(R.layout.activity_maps);
        setupFirebase();
        getSummaryFromTheFirebase();
        // Get the SupportMapFragment and request notification when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    private void setupFirebase() {
        databaseReference = FirebaseDatabase.getInstance().getReference();
    }

    private Map<String, Integer> keywordsMap = new HashMap<>();

    private void getSummaryFromTheFirebase() {
        childTexts = databaseReference.child("Texts");
        Query queryQuestion = childTexts.child("Text").orderByChild("label");
        queryQuestion.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                /*Texts texts = dataSnapshot.getValue(Texts.class);
                keywordsMap = texts.getKeywordsMap();
                Log.d("a1", "" + keywordsMap);*/
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }


    /**
     * Manipulates the map when it's available.
     * The API invokes this callback when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera.
     * In this tutorial, we add polylines and polygons to represent routes and areas on the map.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {

        // Add polylines to the map.
        // Polylines are useful to show a route or some other connection between points.
        /*Polyline polyline1 = googleMap.addPolyline(new PolylineOptions()
                .clickable(true)
                .add(
                        new LatLng(-35.016, 143.321),
                        new LatLng(-34.747, 145.592),
                        new LatLng(-34.364, 147.891),
                        new LatLng(-33.501, 150.217),
                        new LatLng(-32.306, 149.248),
                        new LatLng(-32.491, 147.309)));
        // Store a data object with the polyline, used here to indicate an arbitrary type.
        polyline1.setTag("A");
        // Style the polyline.
        stylePolyline(polyline1);

        List<LatLng> latLngs = new ArrayList<>();

        for (int i = 0; i < keywordsMap.size(); i++) {
            latLngs.add(new LatLng(-50.0 + 10 * i, 120.0 - 10 * i));
        }

        Polyline polyline2 = googleMap.addPolyline(new PolylineOptions()
                .clickable(true)
                .add(new LatLng(-29.501, 119.700),
                        new LatLng(-27.456, 119.672),
                        new LatLng(-25.971, 124.187),
                        new LatLng(-28.081, 126.555),
                        new LatLng(-28.848, 124.229),
                        new LatLng(-28.215, 123.938)));
        polyline2.setTag("B");
        stylePolyline(polyline2);

        for (int i = 0; i < keywordsMap.size(); i++) {
            LatLng sydney = new LatLng(-50.0 + i * 10, 153.040 - i * 10);
            Circle circle = mMap.addCircle(new CircleOptions()
                    .center(new LatLng(-33.87365, 151.20689))
                    .radius(10000)
                    .strokeColor(Color.RED)
                    .fillColor(Color.BLUE));
            Polygon polygon1 = googleMap.addPolygon(new PolygonOptions()
                    .clickable(true)
                    .add(sydney));
            // Store a data object with the polygon, used here to indicate an arbitrary type.
            polygon1.setTag("alpha");
            googleMap.addMarker(new MarkerOptions().position(sydney)
                    .title("Marker in Sydney"));

            // Style the polygon.
            stylePolygon(polygon1);
        }*/

        LatLng latLng1 = new LatLng(-27.457, 153.040);
        LatLng latLng2 = new LatLng(-33.852, 151.211);
        LatLng latLng3 = new LatLng(-37.813, 144.962);
        LatLng latLng4 = new LatLng(-34.928, 138.599);

        // Add polygons to indicate areas on the map.
        Polygon polygon1 = googleMap.addPolygon(new PolygonOptions()
                .clickable(true)
                .add(latLng1, latLng2, latLng3, latLng4));
        // Store a data object with the polygon, used here to indicate an arbitrary type.
        polygon1.setTag("alpha");
        // Style the polygon.
        stylePolygon(polygon1);

        Circle circle1 = googleMap.addCircle(new CircleOptions()
                .center(latLng1)
                .radius(10000)
                .strokeColor(Color.RED)
                .fillColor(Color.BLUE));

        Circle circle2 = googleMap.addCircle(new CircleOptions()
                .center(latLng2)
                .radius(20000)
                .strokeColor(Color.RED)
                .fillColor(Color.BLUE));

        Circle circle3 = googleMap.addCircle(new CircleOptions()
                .center(latLng3)
                .radius(30000)
                .strokeColor(Color.RED)
                .fillColor(Color.BLUE));

        Circle circle4 = googleMap.addCircle(new CircleOptions()
                .center(latLng4)
                .radius(40000)
                .strokeColor(Color.RED)
                .fillColor(Color.BLUE));

        /*Polygon polygon2 = googleMap.addPolygon(new PolygonOptions()
                .clickable(true)
                .add(
                        new LatLng(-31.673, 128.892),
                        new LatLng(-31.952, 115.857),
                        new LatLng(-17.785, 122.258),
                        new LatLng(-12.4258, 130.7932)));
        polygon2.setTag("beta");
        stylePolygon(polygon2);

        LatLng sydney = new LatLng(-33.852, 151.211);
        googleMap.addMarker(new MarkerOptions().position(sydney)
                .title("Marker in Sydney"));
*/
        // Position the map's camera near Alice Springs in the center of Australia,
        // and set the zoom factor so most of Australia shows on the screen.
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(-23.684, 133.903), 4));

        // Set listeners for click events.
        googleMap.setOnPolylineClickListener(this);
        googleMap.setOnPolygonClickListener(this);
    }

    /**
     * Styles the polyline, based on type.
     *
     * @param polyline The polyline object that needs styling.
     */
    private void stylePolyline(Polyline polyline) {
        String type = "";
        // Get the data object stored with the polyline.
        if (polyline.getTag() != null) {
            type = polyline.getTag().toString();
        }

        switch (type) {
            // If no type is given, allow the API to use the default.
            /*case "A":
                // Use a custom bitmap as the cap at the start of the line.
                polyline.setStartCap(
                        new CustomCap(
                                BitmapDescriptorFactory.fromResource(R.drawable.avocado_salad), 10));
                break;*/
            case "B":
                // Use a round cap at the start of the line.
                polyline.setStartCap(new RoundCap());
                break;
        }

        polyline.setEndCap(new RoundCap());
        polyline.setWidth(POLYLINE_STROKE_WIDTH_PX);
        polyline.setColor(COLOR_BLACK_ARGB);
        polyline.setJointType(JointType.ROUND);
    }

    /**
     * Styles the polygon, based on type.
     *
     * @param polygon The polygon object that needs styling.
     */
    private void stylePolygon(Polygon polygon) {
        String type = "";
        // Get the data object stored with the polygon.
        if (polygon.getTag() != null) {
            type = polygon.getTag().toString();
        }

        List<PatternItem> pattern = null;
        int strokeColor = COLOR_BLACK_ARGB;
        int fillColor = COLOR_WHITE_ARGB;

        switch (type) {
            // If no type is given, allow the API to use the default.
            case "alpha":
                // Apply a stroke pattern to render a dashed line, and define colors.
                pattern = PATTERN_POLYGON_ALPHA;
                strokeColor = COLOR_GREEN_ARGB;
                fillColor = COLOR_PURPLE_ARGB;
                break;
            case "beta":
                // Apply a stroke pattern to render a line of dots and dashes, and define colors.
                pattern = PATTERN_POLYGON_BETA;
                strokeColor = COLOR_ORANGE_ARGB;
                fillColor = COLOR_BLUE_ARGB;
                break;
        }

        polygon.setStrokePattern(pattern);
        polygon.setStrokeWidth(POLYGON_STROKE_WIDTH_PX);
        polygon.setStrokeColor(strokeColor);
        polygon.setFillColor(fillColor);
    }

    /**
     * Listens for clicks on a polyline.
     *
     * @param polyline The polyline object that the user has clicked.
     */
    @Override
    public void onPolylineClick(Polyline polyline) {
        // Flip from solid stroke to dotted stroke pattern.
        if ((polyline.getPattern() == null) || (!polyline.getPattern().contains(DOT))) {
            polyline.setPattern(PATTERN_POLYLINE_DOTTED);
        } else {
            // The default pattern is a solid stroke.
            polyline.setPattern(null);
        }

        Toast.makeText(this, "Route type " + polyline.getTag().toString(),
                Toast.LENGTH_SHORT).show();
    }

    /**
     * Listens for clicks on a polygon.
     *
     * @param polygon The polygon object that the user has clicked.
     */
    @Override
    public void onPolygonClick(Polygon polygon) {
        // Flip the values of the red, green, and blue components of the polygon's color.
        int color = polygon.getStrokeColor() ^ 0x00ffffff;
        polygon.setStrokeColor(color);
        color = polygon.getFillColor() ^ 0x00ffffff;
        polygon.setFillColor(color);

        Toast.makeText(this, "Area type " + polygon.getTag().toString(), Toast.LENGTH_SHORT).show();
    }


}


