# Dagger Test Application

It is desirable for Dagger to integrate with how Android view objects are created and destroyed by the platform.
"View Scoped Objects". If an activity is recreated by the platform during configuration change, then the dependencies that are
(re)injected should be the exact same instances as the prior Activity was using. Thus user state is maintained.

This can be achieved via two constructs:

- A PerActivity annotation
 - every instance injected within the scope will be the same
- over-ride the CustomNonConfigurationInstance of FragmentActivity
 - when the platform recreates an Activity, reuse the same Dagger Component

## PerActivityAnnotation
Create a simple annotation:
```java
@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface PerActivity {
}
```

Then use that annotation on the Component and the Module provider methods
```java
@PerActivity
@Subcomponent(modules = ActivityModule.class)
public interface ActivityComponent { ... }
```
```java
@Module
public class ActivityModule {

    @Provides
    @PerActivity // every object instance that should keep scope needs this annotation
    public CoffeeMaker provideCoffeeMaker(){
        return new CoffeeMaker();
    }
}
```
You can also put the *@PerActivity* annotation on an @Inject constructor. We shy away from that due to how those interfere with DaggerMock.
The downside is extra boiler-plate provider methods in the ActivityModule.

## CustomNonConfigurationInstance

The Android framework provides the idea that an object can be stashed away for you while your Activity is being stopped.

```java
    private ActivityComponent component;

    @Override
    public ActivityComponent onRetainCustomNonConfigurationInstance() {
        return component;
    }

    @Override
    public ActivityComponent getLastCustomNonConfigurationInstance() {
        return (ActivityComponent)super.getLastCustomNonConfigurationInstance();
    }

   @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        inject();
        setContentView(R.layout.activity_main);
        ...
    }

    private void inject() {
        component = getLastCustomNonConfigurationInstance();
        if (component == null) {
            component =  ((DaggerApp) getApplication()).getApplicationComponent().plus(new ActivityModule());
        }
        component.inject(this);
    }
```