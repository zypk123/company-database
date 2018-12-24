module.exports = function(grunt){
    'use strict';

	require('load-grunt-tasks')(grunt);
	require('time-grunt')(grunt);

	var config = {
		app: 'src',
		dist: 'dist'
	};

	grunt.initConfig({
		pkg: grunt.file.readJSON('package.json'),
		config: config,

		copy: {
			dist: {
				files: [{
		       		expand: true,
                    cwd: 'src/config',
                    src: '**/*.*', 
                    dest: 'dist/config' 
                },{
		       		expand: true,
                    cwd: 'src/data',
                    src: '**/*.*', 
                    dest: 'dist/data' 
                },{
		       		expand: true,
                    cwd: 'src/frameworks',
                    src: '**/*.*', 
                    dest: 'dist/frameworks' 
                },{
		       		expand: true,
                    cwd: 'src/themes',
                    src: '**/*.*', 
                    dest: 'dist/themes' 
                }]
			}
		},

		clean: {
			dist: {
				src: '<%= config.dist %>/index.html'
			}
		},

		jshint: {
            files: ['src/login.html'],
            options: {
                //覆盖默认配置
                globals: {
                    jQuery: true,
                    console: true,
                    module: true,
                    document: true,
                    sub: true,
                    boss: true,
                    undef: true,
                    exported: true
                }
            }
        },
        htmlmin: {                                     
		    dist: {                                     
		        options: {                                
		      	    removeComments: true,
		      	    collapseWhitespace: true,
		      	    minifyJS: true,
		      	    minifyCSS: true
		        },
		       	files: [{
		       		expand: true,
                    cwd: 'src',
                    src: '**/*.html', 
                    dest: 'dist' 
                }]
			}
		},
		uglify: {
            options: {
                banner: '/*! <%= pkg.name %> <%= grunt.template.today("yyyy-mm-dd") %> */\n' //添加banner
            },
            buildall: {
                options: {
                    mangle: true,
                    compress: {
                        drop_console: true
                    },
                    report: "min" //输出压缩率，可选的值有 false(不输出信息)，gzip
                },
                files: [{
                    expand: true,
                    cwd: 'src/js', //js目录下
                    src: '**/*.js', //所有js文件
                    dest: 'dist/js' //输出到此目录下
                }]
            }
        },
	});

	grunt.registerTask('default', ['copy','htmlmin','uglify']);
};