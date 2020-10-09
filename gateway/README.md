###gateway-router
```json
[
    {
        "id": "core",
        "order": 0,
        "predicates": [{
            "args": {
                "pattern": "/datart-core/**"
            },
            "name": "Path"
        }],
        "filters": [
            {
                "name": "StripPrefix",
                "args":{
                    "parts": "0"
                }
       
            }
        ],
        "uri": "lb://datart-core"
    }
]
```