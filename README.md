# SpinnerDialog
Android Spinner Dialog Library, Use for single or multi selection of choice

[![](https://jitpack.io/v/hamzaahmedkhan/SpinnerDialog.svg)](https://jitpack.io/#hamzaahmedkhan/SpinnerDialog)

[ ![Download](https://api.bintray.com/packages/hamzaahmedkhan/SpinnerDialog/SpinnerDialog/images/download.svg?version=v1.2.2) ](https://bintray.com/hamzaahmedkhan/SpinnerDialog/SpinnerDialog/v1.2.2/link)



## Android UI

<img src='demo/home.png' height=480 width=240 />


<img src='demo/list_0.png' height=480 width=240 />


<img src='demo/list_1.png' height=480 width=240 />


<img src='demo/list_2.png' height=480 width=240 />


<img src='demo/list_3.png' height=480 width=240 />


## Download

To include `SpinnerDialog` in your project, add the following to your dependencies:

**app/build.gradle**
```groovy
dependencies {
        implementation 'com.github.hamzaahmedkhan:SpinnerDialog:v1.2.2'
}
```

## Usage
The following snippet shows how you can use Spinner Dialog in your project.


**In Kotlin**

```kotlin
class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val arraySpinnerModel: ArrayList<SpinnerModel> = ArrayList()

        for (i in 1..9) {
            arraySpinnerModel.add(SpinnerModel("Number $i"))
        }


        // Init single select Fragment
        val spinnerSingleSelectDialogFragment =
            SpinnerDialogFragment.newInstance(
                SpinnerSelectionType.SINGLE_SELECTION,"Spinner Dialog", arraySpinnerModel,
                object :
                    OnSpinnerOKPressedListener {
                    override fun onSingleSelection(data: SpinnerModel, selectedPosition: Int) {
                        Toast.makeText(applicationContext, data.text, Toast.LENGTH_LONG).show()
                    }

                    override fun onMultiSelection(
                        data: List<SpinnerModel>,
                        selectedPosition: Int
                    ) {
                        // It will never send Multi selection data in SINGLE_SELECTION Mode
                    }

                }, 0
            )


        // Init multi select Fragment
        val spinnerMultiSelectDialogFragment =
            SpinnerDialogFragment.newInstance(
                SpinnerSelectionType.MULTI_SELECTION,"Spinner Dialog", arraySpinnerModel,
                object :
                    OnSpinnerOKPressedListener {
                    override fun onSingleSelection(data: SpinnerModel, selectedPosition: Int) {
                        Toast.makeText(applicationContext, data.text, Toast.LENGTH_LONG).show()
                    }

                    override fun onMultiSelection(
                        data: List<SpinnerModel>,
                        selectedPosition: Int
                    ) {
                        Toast.makeText(applicationContext, data.map { it.text }.joinToString(" - "), Toast.LENGTH_LONG).show()
                    }

                }, 0
            )



        txtShowSingleChoiceSpinner.setOnClickListener { spinnerSingleSelectDialogFragment.show(supportFragmentManager, "SpinnerDialogFragmentSingle") }
        txtShowMultiChoiceSpinner.setOnClickListener { spinnerMultiSelectDialogFragment.show(supportFragmentManager, "SpinnerDialogFragmentMulti") }
    }
}
```



**EXTRA ATTRIBUTES**
```kotlin

        // Using optional features for single select dialog
        spinnerSingleSelectDialogFragment.buttonText = "SAVE"
        spinnerSingleSelectDialogFragment.themeColorResId = resources.getColor(R.color.material_pink500)
        spinnerSingleSelectDialogFragment.showSearchBar = true
        spinnerSingleSelectDialogFragment.searchbarHint = "type here to search.."
        spinnerSingleSelectDialogFragment.setDialogHeight(ViewGroup.LayoutParams.MATCH_PARENT) // for dynamic height you can use Integer.dp e.g setDialogHeight(500.dp) or ViewGroup.LayoutParams.WRAP_CONTENT


        // Using optional features for multi select dialog
        spinnerMultiSelectDialogFragment.buttonText = "SAVE"
        spinnerMultiSelectDialogFragment.themeColorResId = resources.getColor(R.color.material_pink500)
        spinnerMultiSelectDialogFragment.showSearchBar = true
        spinnerMultiSelectDialogFragment.searchbarHint = "type here to search.."
        spinnerSingleSelectDialogFragment.setDialogHeight(ViewGroup.LayoutParams.MATCH_PARENT) // for dynamic height you can use Integer.dp e.g setDialogHeight(500.dp) or ViewGroup.LayoutParams.WRAP_CONTENT


```

If you want to use enums list in Enums.

```kotlin
// Declare enums and override toString
enum class TestEnum {

    ENUM1 {
        override fun toString(): String {
            return "enum1"
        }
    },

    ENUM2 {
        override fun toString(): String {
            return "enum2"
        }
    }

}


// initialize spinner ArrayList
val spinnerData: ArrayList<SpinnerModel> = ArrayList()

// If you want to set enum individually
val choice1 = TestEnum.ENUM1
val choice2 = TestEnum.ENUM2

spinnerData.add(SpinnerModel(choice1.toString()))
spinnerData.add(SpinnerModel(choice2.toString()))


// If you want to set all enums values in Spinner
TestEnum.values().forEach {
    spinnerData.add(SpinnerModel(it.toString()))
}
```


**FUTURE RELEASE PLANS**

-> Open issues in Release 1.3.0
