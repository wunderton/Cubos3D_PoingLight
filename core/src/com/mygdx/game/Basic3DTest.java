package com.mygdx.game;

import java.util.ArrayList;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.VertexAttributes.Usage;
import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.environment.DirectionalLight;
import com.badlogic.gdx.graphics.g3d.environment.PointLight;
import com.badlogic.gdx.graphics.g3d.Material;
import com.badlogic.gdx.graphics.g3d.utils.CameraInputController;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;

/**
 * See: http://blog.xoppa.com/basic-3d-using-libgdx-2/
 * @author Xoppa
 */
public class Basic3DTest implements ApplicationListener {
	public Environment environment;
	public PerspectiveCamera cam;
        public PointLight pointLight;
	public CameraInputController camController;
	public ModelBatch modelBatch;
	public Model model;
        public ArrayList<ModelInstance> instances = new ArrayList<ModelInstance>();
        public ArrayList<ModelInstance> cubos = new ArrayList<ModelInstance>();
        public ArrayList<Color> colores = new ArrayList<Color>();

	@Override
	public void create() {
		 environment = new Environment();
        environment.set(new ColorAttribute(ColorAttribute.AmbientLight, 0.4f, 0.4f, 0.4f, 1.0f));
        environment.add(pointLight = new PointLight().set(255f, 255f, 0f, 20f, 10f, 30f, 250f));
		
		modelBatch = new ModelBatch();
		
		cam = new PerspectiveCamera(67, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		cam.position.set(100f, 10f, 50f);
		cam.lookAt(0,0,0);
		cam.near = 1f;
		cam.far = 300f;
		cam.update();

        for (float x = -10f; x <= 100f; x += 6f) {
            ModelBuilder modelBuilder = new ModelBuilder();
            model = modelBuilder.createBox(5f, 5f, 5f, 
            new Material(ColorAttribute.createDiffuse(Color.RED)),
            Usage.Position | Usage.Normal);
            ModelInstance cubo = new ModelInstance(model);
            cubo.transform.setToTranslation(x, 0, 3f);
            instances.add(cubo);
            cubos.add(cubo);
        }

        for (float x = -10f; x <= 100f; x += 6f) {
            ModelBuilder modelBuilder = new ModelBuilder();
            model = modelBuilder.createBox(5f, 5f, 5f, 
            new Material(ColorAttribute.createDiffuse(Color.GREEN)),
            Usage.Position | Usage.Normal);
            ModelInstance cubo = new ModelInstance(model);
            cubo.transform.setToTranslation(x, 0, 12f);
            instances.add(cubo);
            cubos.add(cubo);
        }

        for (float x = -10f; x <= 100f; x += 6f) {
            ModelBuilder modelBuilder = new ModelBuilder();
            model = modelBuilder.createBox(5f, 5f, 5f, 
            new Material(ColorAttribute.createDiffuse(Color.WHITE)),
            Usage.Position | Usage.Normal);
            ModelInstance cubo = new ModelInstance(model);
            cubo.transform.setToTranslation(x, 0, 22f);
            instances.add(cubo);
            cubos.add(cubo);
        }

        camController = new CameraInputController(cam);
        Gdx.input.setInputProcessor(camController);
	}

	@Override
	public void render() {
            camController.update();
		
            Gdx.gl.glViewport(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
            Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);

            modelBatch.begin(cam);
            modelBatch.render(instances, environment);
            modelBatch.end();
	}
	
	@Override
	public void dispose() {
            modelBatch.dispose();
            instances.clear();
	}

	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}
}